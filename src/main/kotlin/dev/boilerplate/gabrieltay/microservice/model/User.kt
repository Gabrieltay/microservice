package dev.boilerplate.gabrieltay.microservice.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "User")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "updated_dt")
    @LastModifiedDate
    val updatedDt: LocalDateTime? = null,

    @Column(name = "created_dt", updatable = false)
    @CreatedDate
    val createdDt: LocalDateTime? = null
)
