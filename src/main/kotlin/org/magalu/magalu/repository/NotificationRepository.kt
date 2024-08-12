package org.magalu.magalu.repository

import org.magalu.magalu.entity.Notification
import org.magalu.magalu.entity.StatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime



interface NotificationRepository : JpaRepository<Notification, Long> {

    fun findByStatusInAndDateTimeBefore(status: List<StatusEntity?>?, dateTime: LocalDateTime?): List<Notification?>?
}