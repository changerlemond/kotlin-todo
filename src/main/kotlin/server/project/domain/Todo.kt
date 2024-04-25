package server.project.domain

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "todos")
class Todo (
    @Id
    val id: Long,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(nullable = false)
    var text: String,

    @Column(nullable = true)
    var status: TodoStatus,

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    var createTime: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    var updateTime: LocalDateTime = LocalDateTime.now()

)