package org.magalu.magalu.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "status")
class StatusEntity {
    @Id
    var statusId: Long? = null

    var description: String? = null

    constructor()

    constructor(statusId: Long?, description: String?) {
        this.statusId = statusId
        this.description = description
    }

    enum class Values(private val id: Long, private val description: String) {
        PENDING(1L, "pending"),
        SUCCESS(2L, "success"),
        ERROR(3L, "error"),
        CANCELED(4L, "canceled");

        fun toStatus(): StatusEntity {
            return StatusEntity(id, description)
        }
    }
}