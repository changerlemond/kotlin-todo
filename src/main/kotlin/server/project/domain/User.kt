package server.project.domain

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.Column
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

class User(
    @Id
    @Tsid
    val id: Long,

    @Column(nullable = false)
    var nickname: String,

    @Column(nullable = false)
    var password: String,

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    var createTime: LocalDateTime = LocalDateTime.now(),
)