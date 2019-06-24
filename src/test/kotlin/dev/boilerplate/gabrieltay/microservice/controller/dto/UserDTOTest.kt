package dev.boilerplate.gabrieltay.microservice.controller.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import dev.boilerplate.gabrieltay.microservice.configuration.UnitTesting
import dev.boilerplate.gabrieltay.microservice.fixtures.UserFixture
import dev.boilerplate.gabrieltay.microservice.model.User

@UnitTesting
class UserDTOTest {
    @Test
    fun `should convert User to UserDTO`() {
        val user = UserFixture.user
        val userDto = user.toDTO()

        assertThat(userDto).isEqualTo(
            UserDTO(
                id = 1,
                name = user.name,
                email = user.email,
                updatedDt = user.updatedDt,
                createdDt = user.createdDt
            )
        )
    }

    @Test
    fun `should convert UserDTO to User`() {
        val userDto = UserFixture.userDTO
        val user = userDto.toEntity()

        assertThat(user).isEqualTo(
            User(
                id = 3L,
                name = userDto.name,
                email = userDto.email,
                updatedDt = userDto.updatedDt,
                createdDt = userDto.createdDt
            )
        )
    }
}
