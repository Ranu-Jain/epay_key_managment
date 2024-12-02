package com.epay.kms.dao;

import com.epay.kms.dto.KeyManagementDto;
import com.epay.kms.entity.KeyAudit;
import com.epay.kms.entity.KeyExpiry;
import com.epay.kms.entity.KeyManagement;
import com.epay.kms.exception.KeyManagementException;
import com.epay.kms.repository.KeyAuditRepository;
import com.epay.kms.repository.KeyExpiryRepository;
import com.epay.kms.repository.KeyManagementRepository;
import com.epay.kms.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * Class Name: KeyManagementDao
 * *
 * Description: This class is used for to save keys and auditLog into DB.
 * *
 * Author: V1017903(Bhushan Wadekar)
 * <p>
 * Copyright (c) 2024 [State Bank of India]
 * All rights reserved
 * *
 * Version:1.0
 */
@Component
@RequiredArgsConstructor
public class KeyManagementDao {

    private final KeyExpiryRepository keyExpiryRepository;
    private final KeyAuditRepository keyAuditRepository;
    private final KeyManagementRepository keyManagementRepository;
    private final ObjectMapper objectMapper;

    /**
     * @param keyId
     * @param actionType
     */
    private static KeyAudit buildKeyAudit(UUID keyId, ActionType actionType) {
        return KeyAudit.builder().actionType(actionType.name()).keyId(keyId).build();
    }

    /**
     * @param KEK
     * @param merchantId
     * @return KeyManagement
     */
    private static KeyManagement buildKeyManagement(KeyManagement.KeyManagementBuilder KEK, String merchantId) {
        return KEK.merchantId(merchantId).status(KeyStatus.ACTIVE.name()).keyEncryptionAlgo(EncryptionAlgorithm.AES_GCM_NoPadding_128.getAlgorithmName()).createdDate(System.currentTimeMillis()).updatedDate(System.currentTimeMillis()).expiryTime(1).build();
    }

    /**
     * @param merchantId
     * @param encryptedKEK
     */
    public void saveKEK(String merchantId, String encryptedKEK) {
        KeyManagement keyManagement = buildKeyManagement(KeyManagement.builder().keyType(KeyType.KEK.name())
                //TODO: once merchant token will ready we will read from them
                .createdBy(System.getProperty("user.name")).encryptedKek(encryptedKEK), merchantId);
        saveKeyGeneration(keyManagement);
    }

    /**
     * @param merchantId
     * @param encryptedKEK
     * @param encryptedMEK
     */
    public void saveMEK(String merchantId, String encryptedKEK, String encryptedMEK) {
        KeyManagement keyManagement = buildKeyManagement(KeyManagement.builder().keyType(KeyType.MEK.name()).encryptedKek(encryptedKEK)
                //TODO: once merchant token will ready we will read from them
                .createdBy(System.getProperty("user.name")).encryptedMek(encryptedMEK), merchantId);
        saveKeyGeneration(keyManagement);
    }

    /**
     * @param keyManagement
     */
    private void saveKeyGeneration(KeyManagement keyManagement) {
        keyManagement = keyManagementRepository.save(keyManagement);
        saveKeyAudit(buildKeyAudit(keyManagement.getId(), ActionType.KEY_GENERATED));
    }

    /**
     * @param keyAudit
     */
    public void saveKeyAudit(KeyAudit keyAudit) {
        keyAuditRepository.save(keyAudit);
    }

    /**
     * @param mid
     * @param keyType
     * @return KeyManagementDto
     */
    public KeyManagementDto getMerchantActiveKeyByKeyType(String mid, KeyType keyType) {
        KeyManagement keyManagement = keyManagementRepository.findActiveByKeyTypeAndMerchantId(keyType.name(), mid).orElseThrow(() -> new KeyManagementException(ErrorConstant.NOT_FOUND_ERROR_CODE, MessageFormat.format(ErrorConstant.NOT_FOUND_ERROR_MESSAGE, "Valid Key", "Merchant Id: " + mid)));
        return objectMapper.convertValue(keyManagement, KeyManagementDto.class);
    }

    /**
     * @param keyExpiry
     */
    public void saveKeyExpiry(KeyExpiry keyExpiry) {
        keyExpiryRepository.save(keyExpiry);
    }

    /**
     * @param keyId
     * @param actionType
     * @return KeyExpiry
     */
    public KeyExpiry buildKeyExpiry(UUID keyId, ActionType actionType) {
        return KeyExpiry.builder().expiryAction("").keyId(keyId).createdDate(1).build();
    }

    /**
     * @param keyManagementDto
     */
    public void updateKeyExpiry(KeyManagementDto keyManagementDto) {
        save(keyManagementDto);
        saveKeyExpiry(buildKeyExpiry(keyManagementDto.getId(), ActionType.EXPIRY));
        saveKeyAudit(buildKeyAudit(keyManagementDto.getId(), ActionType.EXPIRY));
    }

    /**
     * @param keyManagementDto
     */
    public void save(KeyManagementDto keyManagementDto) {
        KeyManagement keyManagement = objectMapper.convertValue(keyManagementDto, KeyManagement.class);
        keyManagementRepository.save(keyManagement);
    }

}
