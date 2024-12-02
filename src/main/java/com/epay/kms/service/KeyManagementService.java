package com.epay.kms.service;

import com.epay.kms.dao.KeyManagementDao;
import com.epay.kms.dao.cache.KMSCacheDao;
import com.epay.kms.dto.KeyManagementDto;
import com.epay.kms.entity.cache.KMSCache;
import com.epay.kms.model.request.KeyExpiryRequest;
import com.epay.kms.model.response.KMSResponse;
import com.epay.kms.util.KeyStatus;
import com.sbi.epay.encryptdecrypt.service.DecryptionService;
import com.sbi.epay.encryptdecrypt.service.EncryptionService;
import com.sbi.epay.encryptdecrypt.service.KeyGeneratorService;
import com.sbi.epay.encryptdecrypt.util.*;
import com.sbi.epay.logging.utility.LoggerFactoryUtility;
import com.sbi.epay.logging.utility.LoggerUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Class Name: KeymgmtService
 * *
 * Description: This class is use for generate KEK and MEK
 * *
 * Author: V1018344(Bhushan Wadekar)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * *
 * Version: 1.0
 */

@Service
@RequiredArgsConstructor
public class KeyManagementService {
    private final KeyGeneratorService keyGeneratorService = new KeyGeneratorService();
    private final KeyManagementDao keyManagementDao;
    private final KMSCacheDao kmsCacheDao;
    private final EncryptionService encryptionService = new EncryptionService();
    private final DecryptionService decryptionService = new DecryptionService();
    LoggerUtility logger = LoggerFactoryUtility.getLogger(this.getClass());

    /**
     * this method is use for KMSCache
     *
     * @param mId
     * @param encryptedKekKey
     * @param encryptedMEKKey
     */
    private static KMSCache buildKMSCache(String mId, String encryptedKekKey, String encryptedMEKKey) {
        return KMSCache.builder().merchantId(mId).encryptedMEK(encryptedMEKKey).encryptedKEK(encryptedKekKey).build();
    }

    /**
     * This method is use for generate KEK and MEK for given  merchant id
     * Params mId
     *
     * @return String
     */
    public KMSResponse<String> generateKey(String mId, int keyLength) {
        logger.info("Initiated  Key Generation for MID {}", mId);

        //Step 1 : Generate AEK
        String aek = "BiIZ5feKr16Td3XSpVywqXlwRNfSy9Gtis04WqEbD/0=";

        //Step 2 : Generate KEK
        String encryptedKekKey = kekKeyGeneration(aek, keyLength);
        keyManagementDao.saveKEK(mId, encryptedKekKey);

        //Step 3 : Generate MEK
        String encryptedMEKKey = mekKeyGeneration(aek, encryptedKekKey, keyLength);
        keyManagementDao.saveMEK(mId, encryptedKekKey, encryptedMEKKey);

        //Step 4 : Save Merchant Key in Cache
        kmsCacheDao.saveKeys(buildKMSCache(mId, encryptedKekKey, encryptedMEKKey));

        logger.info("Key Generation completed for MID {}", mId);
        return KMSResponse.<String>builder().data(List.of(encryptedMEKKey)).build();

    }

    /**
     * this method is use for generate encrypt kek
     *
     * @param aek as string
     * @return String of encrypted KEK.
     */
    private String kekKeyGeneration(String aek, int keyLength) {
        SecretKeyLength secretKeyLength = SecretKeyLength.fromLengthInBits(keyLength);
        String kek = keyGeneratorService.generateKeyByAlgo(secretKeyLength, KeyGenerationAlgo.AES);
        return EncryptionService.encryptSecretKey(kek, aek, EncryptionDecryptionAlgo.AES_GCM_NO_PADDING, GCMIvLength.MAXIMUM, GCMTagLength.STANDARD);
    }

    /**
     * @param aek
     * @param encryptedKekKey
     * @return this method is use for generate encrypt mek
     */
    private String mekKeyGeneration(String aek, String encryptedKekKey, int keyLength) {
        SecretKeyLength secretKeyLength = SecretKeyLength.fromLengthInBits(keyLength);
        String mek = keyGeneratorService.generateKeyByAlgo(secretKeyLength, KeyGenerationAlgo.AES);
        String kek = DecryptionService.decryptKey(encryptedKekKey, aek, EncryptionDecryptionAlgo.AES_GCM_NO_PADDING, GCMIvLength.MAXIMUM, GCMTagLength.STANDARD);
        return EncryptionService.encryptSecretKey(mek, kek, EncryptionDecryptionAlgo.AES_GCM_NO_PADDING, GCMIvLength.MAXIMUM, GCMTagLength.STANDARD);
    }

    /**
     * this method is used for key expire
     *
     * @param mid
     * @param keyExpiryRequest
     * @return
     */
    public KMSResponse<String> keyExpiry(String mid, KeyExpiryRequest keyExpiryRequest) {
        KeyManagementDto keyManagementDto = keyManagementDao.getMerchantActiveKeyByKeyType(mid, keyExpiryRequest.getKeyType());
        if (keyExpiryRequest.getExpiryTime() <= 0L) {
            keyManagementDto.setStatus(KeyStatus.EXPIRED.name());
        } else {
            Date date = new Date(keyExpiryRequest.getExpiryTime());
            keyManagementDto.setExpiryTime(DateUtils.addHours(date, 24).getTime());
        }
        keyManagementDao.updateKeyExpiry(keyManagementDto);
        return KMSResponse.<String>builder().data(List.of("Customer status update Request Completed Successfully.")).status(0).build();
    }


}
