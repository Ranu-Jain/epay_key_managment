package com.epay.kms.shedlockConfig;

import jakarta.persistence.EntityManager;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Class Name: ShedlockConfiguration
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
@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "PT5M")
public class ShedlockConfiguration
{
    @Bean
    public JdbcTemplateLockProvider lockProvider(DataSource dataSource)
    {
        return new JdbcTemplateLockProvider(dataSource);
    }
}
