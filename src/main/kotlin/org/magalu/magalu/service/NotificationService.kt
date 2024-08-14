package org.magalu.magalu.service

import org.magalu.magalu.DTO.AgendamentoDTO
import org.magalu.magalu.entity.Notification
import org.magalu.magalu.entity.StatusEntity
import org.magalu.magalu.repository.NotificationRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer

@Service
class NotificationService(
    private val repository: NotificationRepository,
    private val sendMessage: SendMessage
) {
    fun scheduleNotification(dto: AgendamentoDTO) {
        val notification = dto.toNotification()
        repository.save(notification)
    }

    fun findById(notificationId: Long): Optional<Notification> {
        return repository.findById(notificationId)
    }

    fun cancelNotification(notificationId: Long) {
        val notificationOpt = findById(notificationId)

        if (notificationOpt.isPresent) {
            val notification = notificationOpt.get()
            notification.status = StatusEntity.Values.CANCELED.toStatus()
            repository.save(notification)
        }
    }

    fun checkAndSend(dateTime: LocalDateTime?) {
        val statuses = listOf(
            StatusEntity.Values.PENDING.toStatus(),
            StatusEntity.Values.ERROR.toStatus()
        )

        val notifications = repository.findByStatusInAndDateTimeBefore(statuses, dateTime)

        notifications?.forEach { notification ->
            notification?.let { sendNotification().accept(it) }
        }
    }

    private fun sendNotification(): Consumer<Notification> {
        return Consumer { notification ->
            try {
                sendMessage.send(notification)
                notification.status = StatusEntity.Values.SUCCESS.toStatus()
                repository.save(notification)
            } catch (e: Exception) {
                notification.status = StatusEntity.Values.ERROR.toStatus()
            }
        }
    }
}
