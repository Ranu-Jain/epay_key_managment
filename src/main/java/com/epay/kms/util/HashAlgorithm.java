package com.epay.kms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HashAlgorithm {
    SHA_256("SHA-256", 256, 32),
    SHA_512("SHA-512", 512, 64);

    private final String algorithmName;
    private final int bitLength;
    private final int byteLength;
}
