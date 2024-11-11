package com.epay.kms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Name: ErrorDto
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
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String errorCode;
    private String errorMessage;
}
