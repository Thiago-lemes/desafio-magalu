package org.magalu.magalu.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "channel")
class Channel {
    @Id
    var channelId: Long? = null

    var description: String? = null

    constructor()

    constructor(channelId: Long?, description: String?) {
        this.channelId = channelId
        this.description = description
    }

    enum class Values(private val id: Long, private val description: String) {
        EMAIL(1L, "email"),
        SMS(2L, "sms"),
        PUSH(3L, "push"),
        WHATSAPP(4L, "whatsapp");

        fun toChannel(): Channel {
            return Channel(id, description)
        }
    }
}