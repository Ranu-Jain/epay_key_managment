package com.epay.kms.model.request;

import com.epay.kms.util.KeyType;
import lombok.Data;

@Data
public class KeyExpiryRequest {
    private KeyType keyType;
    private int expiryTime;
}
