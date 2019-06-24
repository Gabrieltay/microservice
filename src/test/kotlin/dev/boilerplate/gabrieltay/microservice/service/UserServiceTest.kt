package dev.boilerplate.gabrieltay.microservice.service

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import dev.boilerplate.gabrieltay.microservice.configuration.UnitTesting
import dev.boilerplate.gabrieltay.microservice.fixtures.UserFixture
import dev.boilerplate.gabrieltay.microservice.repository.UserRepository

@UnitTesting
class UserServiceTest {

    @MockK
    private lateinit var userRepository: UserRepository

    @InjectMockKs
    private lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        clearMocks(userRepository)
        every { userRepository.save(UserFixture.shortUser) } returns UserFixture.user
        every { userRepository.findByIdOrNull(UserFixture.user.id) } returns UserFixture.user
        every { userRepository.deleteById(UserFixture.user.id!!) } just runs
    }

    @Test
    fun `should save user`() {
        assertThat(userService.save(UserFixture.shortUser)).isNotNull
    }

    @Test
    fun `should find user`() {
        assertThat(userService.find(UserFixture.user.id!!)).isNotNull
    }

    @Test
    fun `should delete user`() {
        assertThat(userService.delete(UserFixture.user.id!!))
    }
}
