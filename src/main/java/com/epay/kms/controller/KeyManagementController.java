package com.epay.kms.controller;


import com.epay.kms.model.response.KMSResponse;
import com.epay.kms.service.KeyManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class Name: KeymgmtController
 * *
 * Description:This class is use to send token to service layer for extract merchant id
 * *
 * Author: V1018344(Bhushan Wadekar)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * *
 * Version: 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/key")
public class KeyManagementController {

    private final KeyManagementService keyManagementService;

    @PostMapping("/generation/{mid}")
    public KMSResponse<String> generateKey(@PathVariable String mid) {
        return keyManagementService.generateKey(mid);
    }

}
