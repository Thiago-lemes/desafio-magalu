package org.magalu.magalu.DTO

import org.magalu.magalu.entity.Channel
import org.magalu.magalu.entity.Notification
import org.magalu.magalu.entity.StatusEntity
import java.time.LocalDateTime

data class AgendamentoDTO(
    val dateTime: LocalDateTime,
    val destination: String,
    val message: String,
    val channel: Channel.Values
) {
    fun toNotification(): Notification {
        return Notification(
            dateTime = dateTime,
            destination = destination,
            message = message,
            channel = channel.toChannel(),
            status = StatusEntity.Values.PENDING.toStatus()
        )
    }
}
