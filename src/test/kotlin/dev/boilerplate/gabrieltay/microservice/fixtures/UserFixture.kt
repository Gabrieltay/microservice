package dev.boilerplate.gabrieltay.microservice.fixtures

import dev.boilerplate.gabrieltay.microservice.controller.dto.ExtUserDTO
import dev.boilerplate.gabrieltay.microservice.controller.dto.ExtUsersDTO
import dev.boilerplate.gabrieltay.microservice.controller.dto.UserDTO
import dev.boilerplate.gabrieltay.microservice.model.User
import java.time.LocalDateTime

object UserFixture {
    val user = User(
        id = 1L,
        name = "John Doe",
        email = "johndoe@email.com",
        updatedDt = LocalDateTime.of(2019, 1, 1, 10, 10, 10),
        createdDt = LocalDateTime.of(2019, 1, 1, 10, 10, 10)
    )

    val shortUser = User(
        id = 2L,
        name = "John Doe",
        email = "johndoe@email.com"
    )

    val userDTO = UserDTO(
        id = 3L,
        name = "Mary Jane",
        email = "Mary Jane@email.com",
        updatedDt = LocalDateTime.of(2018, 2, 2, 20, 20, 20),
        createdDt = LocalDateTime.of(2018, 2, 2, 20, 20, 20)
    )

    val shortUserDTO = UserDTO(
        id = 4L,
        name = "Mary Jane",
        email = "Mary Jane@email.com"
    )

    val extUserDTO = ExtUserDTO(
        userId = 100L,
        title = "foo",
        body = "bar",
        id = 101L
    )

    val extUsersDTO = ExtUsersDTO().apply {
        this.add(
            extUserDTO
        )
    }
}
