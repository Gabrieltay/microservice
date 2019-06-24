package dev.boilerplate.gabrieltay.microservice.service

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import dev.boilerplate.gabrieltay.microservice.controller.dto.ExtUserDTO
import dev.boilerplate.gabrieltay.microservice.controller.dto.ExtUsersDTO
import dev.boilerplate.gabrieltay.microservice.model.User
import dev.boilerplate.gabrieltay.microservice.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun save(user: User) {
        userRepository.save(user)
    }

    fun find(userId: Long): User? {
        return userRepository.findByIdOrNull(userId)
    }

    fun delete(userId: Long) {
        userRepository.deleteById(userId)
    }

    fun getExternal(): ExtUsersDTO? {
        // Can make restTemplate as a bean instance
        val restTemplate: RestTemplate = RestTemplateBuilder().build()
        val url = "https://jsonplaceholder.typicode.com/posts"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBasicAuth("user", "pass")

        val response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity<String>(headers), ExtUsersDTO::class.java)
        return response.body
    }

    fun postExternal(): ExtUserDTO? {
        // Can make restTemplate as a bean instance
        val restTemplate: RestTemplate = RestTemplateBuilder().build()
        val url = "https://jsonplaceholder.typicode.com/posts"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON_UTF8
        headers.setBearerAuth("token string")

        val extUserDTO = ExtUserDTO(
            title = "foo",
            body = "bar",
            userId = 2,
            id = 100
        )

        val response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            HttpEntity(extUserDTO, headers),
            ExtUserDTO::class.java
        )
        return response.body
    }
}
