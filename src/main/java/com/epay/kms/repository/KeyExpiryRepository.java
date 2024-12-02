package com.epay.kms.repository;

import com.epay.kms.entity.KeyExpiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Name: KeyExpiryRepository
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
@Repository
public interface KeyExpiryRepository extends JpaRepository<KeyExpiry,Long> {
}
