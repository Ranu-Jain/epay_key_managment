
package com.epay.kms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class Name: KeyManagementException
 * <p>
 * Description:
 * <p>
 * Author: V1018344(Rahul Prajapati)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * <p>
 * Version: 1.0
 */

@Getter
@Setter
@AllArgsConstructor
public class KeyManagementException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
}
