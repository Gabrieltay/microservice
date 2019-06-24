package dev.boilerplate.gabrieltay.microservice.configuration

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@Target(AnnotationTarget.CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
@Tag("unit-test")
annotation class UnitTesting

@Target(AnnotationTarget.CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@Transactional
@Tag("integration")
annotation class IntegrationTesting

abstract class BaseIntegrationTest {
    @Autowired
    private val context: WebApplicationContext? = null

    /**
     * a abstract method to force you init the integration environment
     */
    abstract fun initIntegrationEnvironment()

    @BeforeAll
    fun beforeAll() {
        initIntegrationEnvironment()
    }

    fun buildMockMvc() = buildMockMvcWith { it }
    fun buildMockMvcWith(builder: (DefaultMockMvcBuilder) -> DefaultMockMvcBuilder) =
        builder(MockMvcBuilders.webAppContextSetup(this.context!!)).build()
}
