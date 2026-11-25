package com.melbourne.bit235.wikilogin.service;

import com.melbourne.bit235.wikilogin.model.Account;        
import com.melbourne.bit235.wikilogin.model.LoginForm;      
import com.melbourne.bit235.wikilogin.repository.AuthRepository; 
import org.springframework.stereotype.Service;              

@Service // Spring creates one shared instance and injects it into the Controller.
public class AuthService {

    private final AuthRepository authRepository;

  
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    /**
     * Checks the submitted credentials against the stored account.
     *
     * @param form the login form data bound by Spring MVC
     * @return true if BOTH username and password match exactly, otherwise false
     */
    public boolean authenticate(LoginForm form) {
        // Defensive check: a null form can never be a valid login.
        if (form == null) {
            return false;
        }

        // Ask the repository for the one valid account (no database in Part A).
        Account validAccount = authRepository.findAdminAccount();
        boolean usernameMatches = validAccount.getUsername().equals(form.getUsername());
        boolean passwordMatches = validAccount.getPassword().equals(form.getPassword());

        // Authentication succeeds only when BOTH values match.
        return usernameMatches && passwordMatches;
    }
}
