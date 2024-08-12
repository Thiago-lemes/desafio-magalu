package org.magalu.magalu.controller

import org.magalu.magalu.DTO.AgendamentoDTO
import org.magalu.magalu.entity.Notification
import org.magalu.magalu.service.NotificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/notification")
class NotificationController {
    @RestController
    @RequestMapping("/notifications")
    class NotificationController(private val notificationService: NotificationService) {
        @PostMapping
        fun scheduleNotification(@RequestBody dto: AgendamentoDTO?): ResponseEntity<Void> {
            if (dto != null) {
                notificationService.scheduleNotification(dto)
            }

            return ResponseEntity.accepted().build()
        }

        @GetMapping("/{notificationId}")
        fun getNotification(@PathVariable("notificationId") notificationId: Long?): ResponseEntity<Notification> {
            val notification = notificationService.findById(
                notificationId!!
            )

            if (notification.isEmpty) {
                return ResponseEntity.notFound().build<Notification>()
            }

            return ResponseEntity.ok(notification.get())
        }

        @DeleteMapping("/{notificationId}")
        fun cancelNotification(@PathVariable("notificationId") notificationId: Long?): ResponseEntity<Void> {
            notificationService.cancelNotification(notificationId!!)
            return ResponseEntity.noContent().build()
        }
    }
}