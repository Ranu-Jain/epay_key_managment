package com.epay.kms.scheduler;

import com.epay.kms.entity.KeyManagement;
import com.epay.kms.repository.KeyManagementRepository;
import com.epay.kms.util.KeyStatus;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;


@Component
@RequiredArgsConstructor
public class KeyExpiryScheduler {

    private final KeyManagementRepository keyManagementRepository;
    @Scheduled(cron = "${scheduler.cron.expression}")
    @SchedulerLock(name = "KeyManagement_checkKeyExpiry",lockAtLeastFor = "PT30S",lockAtMostFor = "PT2M")
    public void checkExpiredKeys() {
        Long currentTimeStamp = Instant.now().toEpochMilli();
        List<KeyManagement> expiredKeys = keyManagementRepository.findByStatusAndExpiryTimeLessThan(String.valueOf(KeyStatus.ACTIVE), currentTimeStamp);
        expiredKeys.forEach(keys->
        {
            keys.setStatus(String.valueOf(KeyStatus.EXPIRED));
            keys.setUpdatedDate(Instant.now().toEpochMilli());
            keyManagementRepository.save(keys);
        });
    }
}
