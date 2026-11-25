package com.melbourne.bit235.wikilogin.model;

/**
 * Immutable value object holding one valid username/password pair.
 */
public class Account {

    private final String username; // The accepted administrator username.
    private final String password; // The accepted administrator password.

    /** Creates an account with the given credentials. */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** Returns the stored username. */
    public String getUsername() {
        return username;
    }

    /** Returns the stored password. */
    public String getPassword() {
        return password;
    }
}
