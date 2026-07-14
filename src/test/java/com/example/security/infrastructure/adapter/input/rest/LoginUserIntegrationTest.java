package com.example.security.infrastructure.adapter.input.rest;

import com.example.security.application.dto.LoginCommand;
import com.example.security.application.dto.TokenResult;
import com.example.security.domain.exception.InvalidCredentialsException;
import com.example.security.domain.port.input.LoginUserPort;
import com.example.security.domain.port.input.RegisterUserPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("Login User Integration Test")
class LoginUserIntegrationTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock private RegisterUserPort registerUserPort;
    @Mock private LoginUserPort loginUserPort;

    @BeforeEach
    void setUp() {
        AuthController controller = new AuthController(registerUserPort, loginUserPort);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Should login successfully and return tokens")
    void shouldLoginSuccessfully() throws Exception {
        when(loginUserPort.login(any(LoginCommand.class)))
                .thenReturn(TokenResult.of("access-token", "refresh-token", 3600L));

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                java.util.Map.of("email", "juan@example.com", "password", "password123"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").value("access-token"))
                .andExpect(jsonPath("$.refresh_token").value("refresh-token"))
                .andExpect(jsonPath("$.token_type").value("Bearer"))
                .andExpect(jsonPath("$.expires_in").value(3600));
    }

    @Test
    @DisplayName("Should return unauthorized for invalid credentials")
    void shouldReturnUnauthorized() throws Exception {
        when(loginUserPort.login(any(LoginCommand.class)))
                .thenThrow(new InvalidCredentialsException());

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                java.util.Map.of("email", "wrong@example.com", "password", "password123"))))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid credentials"));
    }
}

