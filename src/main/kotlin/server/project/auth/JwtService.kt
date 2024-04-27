package server.project.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import kotlin.collections.HashMap

@Service
class JwtService {

    private val secretKey: Key = Keys.hmacShaKeyFor("project-server-to-do-rest-api-secret-key".toByteArray())

    private val expirationTime = 12 * 60 * 60 * 1000 // 1d

    fun extractUsername(token: String): String = extractClaim(token, Claims::getSubject)

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String = Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.username)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact()

    private fun extractAllClaims(token: String): Claims = Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .body

    fun generateToken(userDetails: UserDetails): String = generateToken(HashMap(), userDetails)

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)

    private fun getSignInKey(): Key {
        return secretKey
    }

}