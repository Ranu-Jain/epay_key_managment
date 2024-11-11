package com.epay.kms.repository;

import com.epay.kms.entity.KeyManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Name: KeyManagementRepository
 * *
 * Description: This interface is use for find merchant by id and find merchant by id and keyType
 * *
 * Author: V1018344(Rahul Prajapati)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * *
 * Version: 1.0
 */
@Repository
public interface KeyManagementRepository extends JpaRepository<KeyManagement,Long> {


}
