package com.epay.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Class Name: Keymgmt
 * *
 * Description:
 * *
 * Author: V1018344(Bhushan Wadekar)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * *
 * Version: 1.0
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "key_management")
public class KeyManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "merchant_id", nullable = false)
    private String merchantId;

    @Column(name = "key_type")
    private String keyType;

    @Column(name = "encrypted_mek")
    private String encryptedMek;

    @Column(name = "encrypted_kek")
    private String encryptedKek;

    @Column(name = "expiry_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryTime;

    @Column(name = "key_encryption_algo")
    private String keyEncryptionAlgo;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at", nullable = false)
    private long createdAt;

    @Column(name = "updated_at", nullable = false)
    private long updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

}
