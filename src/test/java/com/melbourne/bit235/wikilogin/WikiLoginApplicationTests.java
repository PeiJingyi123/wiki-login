/*
 * Author:      Pei Jingyi
 * Student ID:  S1580654
 * Unit:        BIT235 Object Oriented Programming
 * Assessment:  Assessment 2 - Part A (Wiki Administrator Login)
 * File:        WikiLoginApplicationTests.java
 * Description: Tests for the Service logic and the web request flow. These show
 *              that the correct credentials (pei / 123) succeed, wrong ones fail,
 *              and that GET/POST mappings return the expected views.
 */
package com.melbourne.bit235.wikilogin;

import com.melbourne.bit235.wikilogin.model.LoginForm;       // Form model used in tests.
import com.melbourne.bit235.wikilogin.repository.AuthRepository; // Repository contract.
import com.melbourne.bit235.wikilogin.repository.HardcodedAuthRepository; // Concrete repo.
import com.melbourne.bit235.wikilogin.service.AuthService;   // Service under test.
import org.junit.jupiter.api.Test;                           // JUnit 5 test annotation.
import org.springframework.beans.factory.annotation.Autowired; // Spring dependency injection.
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; // Slice test for MVC.
import org.springframework.boot.test.context.TestConfiguration; // Nested config for test beans.
import org.springframework.context.annotation.Bean;           // Declares a bean in test config.
import org.springframework.test.web.servlet.MockMvc;          // Simulates HTTP requests.

import static org.junit.jupiter.api.Assertions.assertFalse;   // Assertion helpers.
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;   // Build GET.
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;  // Build POST.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;  // Check status.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;    // Check view name.

@WebMvcTest // Loads only the web layer (Controller) plus the beans we provide below.
class WikiLoginApplicationTests {

    @Autowired
    private MockMvc mockMvc; // Spring test helper that sends fake HTTP requests.

    // Provide real Service/Repository beans so the Controller can be constructed.
    @TestConfiguration
    static class TestBeans {
        @Bean
        AuthRepository authRepository() {
            return new HardcodedAuthRepository();
        }

        @Bean
        AuthService authService(AuthRepository repo) {
            return new AuthService(repo);
        }
    }

    /** GET /login should render the login view with HTTP 200 OK. */
    @Test
    void showLoginPageReturnsLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /** Correct credentials should render the welcome view. */
    @Test
    void correctCredentialsReturnWelcomeView() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "pei")
                        .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }

    /** Wrong credentials should render the error view. */
    @Test
    void wrongCredentialsReturnErrorView() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "wrong")
                        .param("password", "wrong"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    /** Service directly: correct credentials authenticate successfully. */
    @Test
    void serviceAcceptsCorrectCredentials() {
        AuthService service = new AuthService(new HardcodedAuthRepository());
        assertTrue(service.authenticate(new LoginForm("pei", "123")));
    }

    /** Service directly: incorrect credentials are rejected. */
    @Test
    void serviceRejectsWrongCredentials() {
        AuthService service = new AuthService(new HardcodedAuthRepository());
        assertFalse(service.authenticate(new LoginForm("pei", "wrong")));
        assertFalse(service.authenticate(new LoginForm("wrong", "123")));
    }
}
