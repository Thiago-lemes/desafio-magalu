package org.magalu.magalu.service

import org.magalu.magalu.entity.Channel
import org.magalu.magalu.entity.Notification
import org.springframework.stereotype.Service

@Service
class SendMessage {
    fun send(notification: Notification) {
        when (notification.channel?.channelId) {
            Channel.Values.EMAIL.id -> emailSender(notification)
            Channel.Values.SMS.id -> smsSender(notification)
            Channel.Values.PUSH.id -> pushSender(notification)
            Channel.Values.WHATSAPP.id -> whatsaapSender(notification)
            else -> throw IllegalArgumentException("Unsupported channel: ${notification.channel}")
        }
    }

    fun emailSender(notification: Notification) {}
    fun smsSender(notification: Notification) {}
    fun pushSender(notification: Notification) {}
    fun whatsaapSender(notification: Notification) {}
}