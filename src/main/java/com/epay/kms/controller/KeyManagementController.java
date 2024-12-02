package com.epay.kms.controller;


import com.epay.kms.model.request.KeyExpiryRequest;
import com.epay.kms.model.response.KMSResponse;
import com.epay.kms.service.KeyManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Class Name: KeymgmtController
 * *
 * Description:This class is use to generate key and expiry key
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

    /**
     * this method is used for generate key
     * @param mid
     *
     */
    @PostMapping("/generation/{mid}/{keyLength}")
    public KMSResponse<String> generateKey(@PathVariable String mid,@PathVariable int keyLength)  {
        return keyManagementService.generateKey(mid,keyLength);
    }

    /**
     * this method is use for set key expiry status and expiry time.
     *
     * @param mid              as string
     * @param keyExpiryRequest as object
     * @return kms response
     */
    @PostMapping("/expiry/{mid}")
    public KMSResponse<String> keyExpiration(@PathVariable String mid, @RequestBody KeyExpiryRequest keyExpiryRequest) {
        return keyManagementService.keyExpiry(mid, keyExpiryRequest);
    }

}
