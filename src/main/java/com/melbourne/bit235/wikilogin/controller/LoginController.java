package com.melbourne.bit235.wikilogin.controller;

import com.melbourne.bit235.wikilogin.model.LoginForm; // The form-backing model object.
import com.melbourne.bit235.wikilogin.service.AuthService; // Business logic provider.
import jakarta.validation.Valid;                  // Triggers @NotBlank validation on the form.
import org.springframework.stereotype.Controller; // Registers this class as a web Controller bean.
import org.springframework.ui.Model;              // Carries data from Controller to View.
import org.springframework.validation.BindingResult; // Holds the outcome of form validation.
import org.springframework.web.bind.annotation.GetMapping;  // Handles HTTP GET requests.
import org.springframework.web.bind.annotation.ModelAttribute; // Binds form fields to an object.
import org.springframework.web.bind.annotation.PostMapping; // Handles HTTP POST requests.

@Controller // Marks this class as an MVC controller handled by Spring's DispatcherServlet.
public class LoginController {

    private final AuthService authService;

    /** Constructor injection of the Service layer. */
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Step 1 of the flow: show the login form.
     * GET  /            -> redirect to /login
     * GET  /login       -> return the "login" template (login.html).
     */
    @GetMapping({"/", "/login"}) // Maps BOTH the root URL and /login to this method.
    public String showLoginPage(Model model) {
        // Add an empty form object so Thymeleaf can bind fields via th:field.
        model.addAttribute("loginForm", new LoginForm());
        return "login"; // Resolves to src/main/resources/templates/login.html.
    }

    /**
     * Step 2 of the flow: process the submitted username and password.
     * POST /login -> welcome page if valid, error page otherwise.
     *
     * @Valid asks Spring to check @NotBlank; BindingResult captures any errors
     *        so the controller can return the form with messages instead of throwing.
     */
    @PostMapping("/login") // Handles the form's action="/login" method="post".
    public String processLogin(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                               BindingResult bindingResult,
                               Model model) {

        // If required fields are empty, stay on the login page and show errors.
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Please enter both a username and a password.");
            return "login";
        }

        // Delegate the credential check to the Service layer (no logic here).
        boolean authenticated = authService.authenticate(loginForm);

        if (authenticated) {
            // Success: pass the username to the View so it can say "Welcome, pei".
            model.addAttribute("username", loginForm.getUsername());
            return "welcome"; // Renders welcome.html with a welcome message.
        } else {
            // Failure: pass a helpful message to the View and render error.html.
            model.addAttribute("errorMessage",
                    "Invalid username or password. Please try again.");
            return "error"; // Renders error.html with the error message.
        }
    }
}
