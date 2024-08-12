package org.magalu.magalu.repository

import org.magalu.magalu.entity.StatusEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StatusRepository: JpaRepository<StatusEntity, Long> {
}