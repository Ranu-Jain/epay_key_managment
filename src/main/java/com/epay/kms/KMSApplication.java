package com.epay.kms;

import com.sbi.epay.util.service.KeyGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * SpringBootApplication for Keymgmt Service
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"org.springframework.boot.context.embedded.tomcat", "com.epay.kms"})
@EnableJpaRepositories(basePackages = "com.epay.kms")
@EntityScan(basePackages = "com.epay.kms")
public class KMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(KMSApplication.class, args);
    }

    @Bean
    public KeyGeneratorService getKeyGeneratorService() {
        return new KeyGeneratorService();
    }
}
