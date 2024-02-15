package org.waffl.diver.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMessageUtil {
    @Scheduled(fixedRate = 5000) // time in milliseconds
    public void printMessage() {
        System.out.println("Splash! I'm diving!");
    }
}
