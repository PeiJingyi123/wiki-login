package com.melbourne.bit235.wikilogin.model;
import jakarta.validation.constraints.NotBlank; // Bean Validation annotation for required text.

public class LoginForm {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    public LoginForm() {
    }

    /** Convenience constructor useful for writing unit tests. */
    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    /** Setter called by Spring MVC when binding the submitted username. */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Getter for the password entered in the form. */
    public String getPassword() {
        return password;
    }

    /** Setter called by Spring MVC when binding the submitted password. */
    public void setPassword(String password) {
        this.password = password;
    }
}
