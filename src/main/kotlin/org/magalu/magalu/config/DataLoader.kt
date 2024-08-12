package org.magalu.magalu.config

import org.magalu.magalu.entity.Channel
import org.magalu.magalu.entity.StatusEntity
import org.magalu.magalu.repository.ChannelRepository
import org.magalu.magalu.repository.StatusRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoader(
    private val channelRepository: ChannelRepository,
    private val statusRepository: StatusRepository
) : CommandLineRunner {
    override fun run(vararg args: String) {
        Channel.Values.values()
            .map { it.toChannel() }
            .forEach { channelRepository.save(it) }

        StatusEntity.Values.values()
            .map { it.toStatus() }
            .forEach { statusRepository.save(it) }
    }
}
