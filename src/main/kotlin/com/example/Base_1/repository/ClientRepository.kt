package com.example.Base_1.repository

import com.example.Base_1.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long?> {
    fun findById (id: Long?): Client?
}