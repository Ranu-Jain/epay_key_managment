package com.epay.kms.service;


import com.epay.kms.dao.KeyManagementDao;
import com.epay.kms.model.response.KMSResponse;
import com.epay.kms.util.HashAlgorithm;
import com.sbi.epay.logging.utility.LoggerFactoryUtility;
import com.sbi.epay.logging.utility.LoggerUtility;
import com.sbi.epay.util.service.EncryptionService;
import com.sbi.epay.util.service.KeyGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class Name: KeyManagementService
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

    private final KeyManagementDao keyManagementDao;
    private final KeyGeneratorService keyGeneratorService;
    LoggerUtility logger = LoggerFactoryUtility.getLogger(this.getClass());

    @Transactional
    public KMSResponse<String> generateKey(String mId) {
        logger.info("Initiated  Key Generation for MID {}", mId);
        //Step 1 : Get AEK
        //TODO : Get the AEK from DB or configuration or Some other place
        String encodedAEK = "BiIZ5feKr16Td3XSpVywqXlwRNfSy9Gtis04WqEbD/0=";
        //Step 2 : Generate KEK
        String encryptedKeKKey = keyGeneration(encodedAEK);
        keyManagementDao.saveKEK(mId, encryptedKeKKey);
        //Step 3 : Generate MEK
        String encryptedMEKKey = keyGeneration(encryptedKeKKey);
        keyManagementDao.saveMEK(mId, encryptedKeKKey, encryptedMEKKey);
        logger.info("Key Generation completed for MID {}", mId);
        return KMSResponse.<String>builder().data(List.of(encryptedMEKKey)).build();
    }

    private String keyGeneration(String keyEncryptedBy){
        String keyValue = keyGeneratorService.generateKey(HashAlgorithm.SHA_256.getBitLength());
        return EncryptionService.encryptKeK(keyValue, keyEncryptedBy);
    }

}

