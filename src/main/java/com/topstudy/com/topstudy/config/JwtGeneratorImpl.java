package com.topstudy.com.topstudy.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.topstudy.com.topstudy.model.User;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.message}")
    private String message;
    private static final Logger logger = LogManager.getLogger(JwtGeneratorImpl.class);

    @Override
    public Map<String, String> generateToken(User user) {
        try {
            // Log the secret value for debugging
            logger.debug("Using secret: {}", secret);

            String jwtToken = Jwts.builder()
                    .setSubject(user.getUserName())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

            Map<String, String> jwtTokenGen = new HashMap<>();
            jwtTokenGen.put("token", jwtToken);
            jwtTokenGen.put("message", message);
            return jwtTokenGen;
        } catch (Exception e) {
            // Handle exception, e.g., log it or throw a custom exception
            // You can also define a custom exception class for JWT-related errors
            throw new RuntimeException("JWT generation failed: " + e.getMessage(), e);
        }
    }
}
