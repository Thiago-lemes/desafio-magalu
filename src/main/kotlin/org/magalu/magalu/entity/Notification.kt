package org.magalu.magalu.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notification")
data class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    var id : Long = 0L,

    var dateTime: LocalDateTime? = null,

    var destination : String? = null,

    var message : String? = null,

    @ManyToOne
    @JoinColumn(name = "channel", nullable = false)
    var channel : Channel? = null,

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    var status : StatusEntity? = null,
)