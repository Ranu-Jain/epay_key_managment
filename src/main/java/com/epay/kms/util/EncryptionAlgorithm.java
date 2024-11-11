package com.epay.kms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EncryptionAlgorithm {
    AES_GCM_NoPadding_128("AES/GCM/NoPadding", 128),
    AES_GCM_NoPadding_256("AES/GCM/NoPadding", 256);

    private final String algorithmName;
    private final int byteLength;
}
