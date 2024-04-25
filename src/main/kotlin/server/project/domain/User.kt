package server.project.domain

import io.hypersistence.tsid.TSID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    var nickname: String,

    @Column(nullable = false)
    var password: String,
) {
    @Id
    val id: Long = TSID.TSID_EPOCH

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    var createTime: LocalDateTime = LocalDateTime.now()
}