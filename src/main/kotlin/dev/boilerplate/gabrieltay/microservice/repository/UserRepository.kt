package dev.boilerplate.gabrieltay.microservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import dev.boilerplate.gabrieltay.microservice.model.User

@Repository
interface UserRepository : JpaRepository<User, Long>
