package dev.boilerplate.gabrieltay.microservice.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import dev.boilerplate.gabrieltay.microservice.controller.dto.ExtUserDTO
import dev.boilerplate.gabrieltay.microservice.controller.dto.ExtUsersDTO
import dev.boilerplate.gabrieltay.microservice.controller.dto.UserDTO
import dev.boilerplate.gabrieltay.microservice.controller.dto.toDTO
import dev.boilerplate.gabrieltay.microservice.controller.dto.toEntity
import dev.boilerplate.gabrieltay.microservice.service.UserService

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/user")
    fun saveOrUpdateUser(@RequestBody userDTO: UserDTO) {
        logger.info("Receive save user request")
        userService.save(userDTO.toEntity())
    }

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable userId: Long): UserDTO? {
        return userService.find(userId)?.toDTO()
    }

    @DeleteMapping("/users/{userId}")
    fun deleteUser(@PathVariable userId: Long) {
        userService.delete(userId)
    }

    @GetMapping("/external/users")
    fun getExternalUsers(): ExtUsersDTO? {
        logger.debug("Test DEBUG logging")
        logger.info("Test INFO logging")
        logger.warn("Test WARN logging")
        logger.error("Test ERROR logging")
        return userService.getExternal()
    }

    @PostMapping("/external/users")
    fun postExternalUsers(): ExtUserDTO? {
        return userService.postExternal()
    }
}
