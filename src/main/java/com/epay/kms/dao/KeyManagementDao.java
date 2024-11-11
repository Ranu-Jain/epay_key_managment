package com.epay.kms.dao;

import com.epay.kms.entity.KeyManagement;
import com.epay.kms.repository.KeyManagementRepository;
import com.epay.kms.util.EncryptionAlgorithm;
import com.epay.kms.util.KeyStatus;
import com.epay.kms.util.KeyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Class Name: KeyManagementDao
 * *
 * Description:
 * *
 * Author: V1017903(bhushan wadekar)
 * <p>
 * Copyright (c) 2024 [State Bank of India]
 * All rights reserved
 * *
 * Version:1.0
 */
@Component
@RequiredArgsConstructor
public class KeyManagementDao {
    private final KeyManagementRepository keyManagementRepository;

    public void saveKEK(String merchantId, String encryptedKEK) {
        KeyManagement keyManagement = buildKeyManagment(KeyManagement.builder()
                .keyType(KeyType.KEK.name())
                .encryptedKek(encryptedKEK), merchantId);
        keyManagementRepository.save(keyManagement);
    }
    public void saveMEK(String merchantId, String encryptedKEK, String encryptedMEK) {
        KeyManagement keyManagement = buildKeyManagment(KeyManagement.builder()
                .keyType(KeyType.MEK.name())
                .encryptedKek(encryptedKEK)
                .encryptedMek(encryptedMEK), merchantId);
        keyManagementRepository.save(keyManagement);
    }


    private static KeyManagement buildKeyManagment(KeyManagement.KeyManagementBuilder KEK, String merchantId) {
        return KEK
                .merchantId(merchantId)
                .status(KeyStatus.ACTIVE.name())
                .keyEncryptionAlgo(EncryptionAlgorithm.AES_GCM_NoPadding_128.getAlgorithmName())
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .build();
    }

}
