package com.personalfinance.manager.security;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        String TEST_SECRET = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";
        ReflectionTestUtils.setField(jwtService, "secret", TEST_SECRET);
        ReflectionTestUtils.setField(jwtService, "expiration", 900000L);

    }

    @Test
    void generateToken_ShouldReturnToken_WhenUserIdIsValid() {
        // Arrange
        Long userId = 1L;

        // Act
        String token = jwtService.generateToken(userId);

        // Assert
        assertNotNull(token);
        assertFalse(token.isBlank());

    }

    @Test
    void extractUserId_ShouldReturnCorrectUserId_WhenTokenIsValid() {
        // Arrange
        Long expectedUserId = 1L;
        String token = jwtService.generateToken(expectedUserId);

        // Act
        Long actualUserId = jwtService.extractUserId(token);

        // Assert
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void isTokenValid_ShouldReturnTrue_WhenTokenIsValid() {
        // Arrange
        String token = jwtService.generateToken(1L);

        // Act
        boolean result = jwtService.isTokenValid(token);

        // Assert
        assertTrue(result);


    }

    @Test
    void isTokenValid_ShouldReturnFalse_WhenTokenIsExpired() throws InterruptedException{
        // Arrange
       ReflectionTestUtils.setField(jwtService, "expiration", 1000L);
       String token = jwtService.generateToken(1L);

        // Act
        Thread.sleep(1500);
        boolean result = jwtService.isTokenValid(token);

        // Assert
        assertFalse(result);
    }

    @Test
    void isTokenValid_ShouldReturnFalse_WhenTokenisTampered() throws InterruptedException{
        // Arrange
       String token = jwtService.generateToken(1L);

       String tamperedToken = token + "modified";

       // Act
        boolean result = jwtService.isTokenValid(tamperedToken);

        // Assert
        assertFalse(result);
    }
}
