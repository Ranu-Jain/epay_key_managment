package com.epay.kms.repository;

import com.epay.kms.entity.KeyAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Name: KeyAuditRepository
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
public interface KeyAuditRepository extends JpaRepository<KeyAudit,Long> {
}
