package server.project.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    var nickname: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var roles: MutableSet<UserRole>,
) {
    @Id
    val id: Long = UUID.randomUUID().mostSignificantBits

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    var createTime: LocalDateTime = LocalDateTime.now()
}