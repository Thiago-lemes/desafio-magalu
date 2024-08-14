package org.magalu.magalu.service

import org.magalu.magalu.entity.Channel
import org.magalu.magalu.entity.Notification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class SendMessage @Autowired constructor(
    private val mailSender: JavaMailSender,
    @Value("\${spring.mail.username}") private val remetente: String
) {
    private val logger: Logger = LoggerFactory.getLogger(SendMessage::class.java)

    fun send(notification: Notification) {
        when (notification.channel?.channelId) {
            Channel.Values.EMAIL.id -> emailSender(notification)
            Channel.Values.SMS.id -> smsSender(notification)
            Channel.Values.PUSH.id -> pushSender(notification)
            Channel.Values.WHATSAPP.id -> whatsappSender(notification)
            else -> throw IllegalArgumentException("Unsupported channel: ${notification.channel}")
        }
    }

    private fun emailSender(notification: Notification) {
        val destination = notification.destination
        try {
            val simpleMailMessage = SimpleMailMessage().apply {
                from = remetente
                setTo(destination)
                subject = "Teste"
                text = notification.message
            }
            mailSender.send(simpleMailMessage)
            logger.info("Email sent successfully to $destination")
        } catch (e: Exception) {
            logger.error("Error sending email: ${e.message}", e)
            throw e
        }
    }

    private fun smsSender(notification: Notification) {}

    private fun pushSender(notification: Notification) {}

    private fun whatsappSender(notification: Notification) {}
}
