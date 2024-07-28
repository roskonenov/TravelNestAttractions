package bg.softuni.travelNestAccount.service.impl;

import bg.softuni.travelNestAccount.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

@Service
public class JWTServiceImpl implements JWTService {

    private final String jwtSecret;

    public JWTServiceImpl(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @Override
    public UserDetails extractUserDetails(String jwtToken) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        List<String> roles = getRoles(claims);
        String username = getUsername(claims);

        return new User(username, "",
                roles.stream().map(SimpleGrantedAuthority::new).toList());
    }

    private String getUsername(Claims claims) {
        return claims.get("username", String.class);
    }

    @SuppressWarnings("unchecked")
    private List<String> getRoles(Claims claims) {
        return claims.get("roles", List.class);
    }

    private Key getSigningKey(){
        return Keys
                .hmacShaKeyFor(jwtSecret
                        .getBytes(StandardCharsets.UTF_8));
    }
}
