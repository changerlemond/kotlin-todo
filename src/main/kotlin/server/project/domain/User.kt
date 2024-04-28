package server.project.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    var nickname: String,

    @Column(nullable = false)
    private var password: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<UserRole>,
): UserDetails {

    @Id
    val id: Long = UUID.randomUUID().mostSignificantBits

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    var createdDateTime: LocalDateTime = LocalDateTime.now()
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.nickname
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}