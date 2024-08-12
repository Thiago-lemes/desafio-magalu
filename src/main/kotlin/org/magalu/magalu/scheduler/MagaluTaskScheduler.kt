package org.magalu.magalu.scheduler

import org.magalu.magalu.service.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Component
class MagaluTaskScheduler(private val notificationService: NotificationService) {

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    fun checkTasks() {
        val dateTime = LocalDateTime.now()
        logger.info("Running at {}", dateTime)
        notificationService.checkAndSend(dateTime)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MagaluTaskScheduler::class.java)
    }
}
