package com.epay.kms;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.sbi.epay.encryptdecrypt.service.KeyGeneratorService;
import com.sbi.epay.hazelcast.service.HazelcastService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBootApplication for KMS Service
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"org.springframework.boot.context.embedded.tomcat", "com.epay.kms", "com."})
@EnableJpaRepositories(basePackages = "com.epay.kms")
@EntityScan(basePackages = "com.epay.kms")
@EnableScheduling
public class KMSApplication {

    private static final String CLUSTER_NAME = "Epay_Key_Management";

    public static void main(String[] args) {
        SpringApplication.run(KMSApplication.class, args);
    }

    @Bean
    public KeyGeneratorService getKeyGeneratorService() {
        return new KeyGeneratorService();
    }

    @Bean
    public HazelcastInstance buidHazelcastInstance() {
        Config config = new Config();
        config.setClusterName(CLUSTER_NAME);
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public HazelcastService buidHazelcastService() {
        return new HazelcastService();
    }
}