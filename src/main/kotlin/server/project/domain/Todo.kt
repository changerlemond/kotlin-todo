package server.project.domain

import io.hypersistence.tsid.TSID
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "todos")
class Todo (
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(nullable = false)
    var text: String,
) {
    @Id
    val id: Long = TSID.TSID_EPOCH

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var status: TodoStatus = TodoStatus.READY

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    var createTime: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    var updateTime: LocalDateTime = LocalDateTime.now()
}