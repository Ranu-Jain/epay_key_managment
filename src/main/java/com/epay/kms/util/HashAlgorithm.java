package com.epay.kms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class Name: HashAlgorithm
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
public enum HashAlgorithm {
    AES_128("AES", 128, 32),
    AES_256("AES", 256, 32),
    HMACSHA_512("HmacSHA512", 512, 64);

    private final String algorithmName;
    private final int bitLength;
    private final int byteLength;

    public static HashAlgorithm getByAlgorithmName(String algorithmName){
       if(algorithmName == null || algorithmName.isBlank())
       {
           throw new IllegalArgumentException("Algorithm name cannot be null or blank");
       }
       switch(algorithmName.toUpperCase())
       {
           case "AES-128":
               return AES_128;
           case "AES-256":
               return AES_256;
           case "HMACSHA-512":
               return HMACSHA_512;

           default:
               throw new IllegalArgumentException("Invalid Algorithm : "+algorithmName);
       }
    }
}
