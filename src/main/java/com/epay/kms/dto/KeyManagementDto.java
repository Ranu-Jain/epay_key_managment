package com.epay.kms.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Class Name: KeyManagementDto
 * *
 * Description:
 * *
 * Author: V1018344(Rahul Prajapati)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * *
 * Version: 1.0
 */

@Data
@Builder
public class KeyManagementDto {

    private String merchantId;
    private String keyType;
    private String encryptedMek;
    private String encryptedKek;
    private long expiryTime;
    private String keyHashAlgo;
    private String status;
    private long createdAt;
    private long updatedAt;
    private String createdBy;
    private String updatedBy;

}
