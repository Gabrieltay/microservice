package dev.boilerplate.gabrieltay.microservice.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import dev.boilerplate.gabrieltay.microservice.model.User
import java.time.LocalDateTime

data class UserDTO(
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("updatedDt")
    val updatedDt: LocalDateTime? = null,

    @JsonProperty("createdDt")
    val createdDt: LocalDateTime? = null
)

fun User.toDTO(): UserDTO = UserDTO(id, name, email, updatedDt, createdDt)

fun UserDTO.toEntity(): User = User(id, name, email, updatedDt, createdDt)

data class ExtUserDTO(
    @JsonProperty("userId")
    val userId: Long,

    @JsonProperty("id")
    val id: Long,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("body")
    val body: String
)

class ExtUsersDTO : ArrayList<ExtUserDTO>()
