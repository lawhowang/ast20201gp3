package ast20201.project.util;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ast20201.project.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static int jwtExpirationInMs = 604800000;

    public String generateToken(Long userId, String username) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	ObjectNode userInfo = mapper.createObjectNode();
    	userInfo.put("id", userId);
    	userInfo.put("username", username);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userInfo);
    	System.out.println(json);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        
        
        return Jwts.builder()
                .setSubject(json)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (JwtException ex) {
            logger.error("Invalid JWT signature");
        }
        return false;
    }
}