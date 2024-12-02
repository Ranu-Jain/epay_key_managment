package com.epay.kms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class Name: EncryptionAlgorithm
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
@Getter
@AllArgsConstructor
public enum EncryptionAlgorithm
{
    AES_GCM_NoPadding_128("AES/GCM/NoPadding", 128),
    AES_GCM_NoPadding_256("AES/GCM/NoPadding", 256);

    private final String algorithmName;
    private final int byteLength;
}
