package org.magalu.magalu.repository

import org.magalu.magalu.entity.Channel
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelRepository: JpaRepository<Channel, Long> {
}