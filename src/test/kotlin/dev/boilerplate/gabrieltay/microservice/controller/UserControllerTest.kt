package dev.boilerplate.gabrieltay.microservice.controller

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import dev.boilerplate.gabrieltay.microservice.configuration.UnitTesting
import dev.boilerplate.gabrieltay.microservice.fixtures.UserFixture
import dev.boilerplate.gabrieltay.microservice.service.UserService

@UnitTesting
class UserControllerTest {

    @MockK
    private lateinit var userService: UserService

    @InjectMockKs
    private lateinit var userController: UserController

    @BeforeEach
    fun setup() {
        clearMocks(userService)
        every { userService.save(any()) } just runs
        every { userService.find(1L) } returns UserFixture.user
        every { userService.find(0L) } returns null
        every { userService.delete(any()) } just runs
        every { userService.getExternal() } returns UserFixture.extUsersDTO
        every { userService.postExternal() } returns UserFixture.extUserDTO
    }

    @Test
    fun `should save user`() {
        assertThat(userController.saveOrUpdateUser(UserFixture.shortUserDTO))
    }

    @Test
    fun `should delete user`() {
        assertThat(userController.deleteUser(1L))
    }

    @Test
    fun `should get user`() {
        assertThat(userController.getUser(1L)).isNotNull
    }

    @Test
    fun `unable to get user`() {
        assertThat(userController.getUser(0)).isNull()
    }

    @Test
    fun `should get external user`() {
        assertThat(userController.getExternalUsers()?.size).isEqualTo(1)
    }

    @Test
    fun `should post external user`() {
        val extUserDTO = userController.postExternalUsers()
        assertThat(extUserDTO?.title).isEqualTo("foo")
        assertThat(extUserDTO?.body).isEqualTo("bar")
        assertThat(extUserDTO?.userId).isEqualTo(100L)
        assertThat(extUserDTO?.id).isEqualTo(101L)
    }
}
