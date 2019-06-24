package dev.boilerplate.gabrieltay.microservice.integration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import dev.boilerplate.gabrieltay.microservice.configuration.BaseIntegrationTest
import dev.boilerplate.gabrieltay.microservice.configuration.IntegrationTesting
import dev.boilerplate.gabrieltay.microservice.fixtures.UserFixture

@SpringBootTest
@IntegrationTesting
class UserControllerTest : BaseIntegrationTest() {
    private lateinit var mockMvc: MockMvc

    private val jsonMapper = jacksonObjectMapper()

    override fun initIntegrationEnvironment() {
        mockMvc = buildMockMvcWith {
            it.apply(SecurityMockMvcConfigurers.springSecurity())
        }
    }

    @Test
    fun `should get list of users from external source`() {
        mockMvc.perform(
            get("/api/external/users")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(100))
    }

    @Test
    fun `should post user to external source`() {
        mockMvc.perform(
            post("/api/external/users")
        )
            .andExpect(status().is2xxSuccessful)
            .andExpect(jsonPath("$.title").value("foo"))
            .andExpect(jsonPath("$.body").value("bar"))
            .andExpect(jsonPath("$.userId").value(2))
    }

    @Test
    fun `should save or update user`() {

        val userDto = UserFixture.shortUserDTO
        val jsonStr = jsonMapper.writeValueAsString(userDto)

        mockMvc.perform(
            post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)
        )
            .andExpect(status().is2xxSuccessful)
    }
}
