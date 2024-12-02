package com.epay.kms.dto;

import lombok.Data;

import java.util.UUID;

/**
 * Class Name: KeyManagementDto
 * *
 * Description: This class contains record(s) related to KeyManagement.
 * *
 * Author: V1018344(Rahul Prajapati)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * *
 * Version: 1.0
 */
@Data
public class KeyManagementDto {

    private UUID id;
    private String merchantId;
    private String keyType;
    private String encryptedMek;
    private String encryptedKek;
    private long expiryTime;
    private String keyEncryptionAlgo;
    private String status;
    private long createdDate;
    private long updatedDate;
    private String createdBy;
    private String updatedBy;

}
