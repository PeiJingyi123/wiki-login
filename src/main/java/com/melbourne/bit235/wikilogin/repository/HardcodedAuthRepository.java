package com.melbourne.bit235.wikilogin.repository;

import com.melbourne.bit235.wikilogin.model.Account; // Value object returned to the service.
import org.springframework.stereotype.Repository;    // Registers this class as a Spring Repository bean.

@Repository // Tells Spring to create and manage one instance of this class.
public class HardcodedAuthRepository implements AuthRepository {

    // The ONE acceptable username: first name in lower case, as required by the brief.
    private static final String VALID_USERNAME = "pei";
    // The ONE acceptable password, as required by the brief.
    private static final String VALID_PASSWORD = "123";

    /**
     * Returns the hard coded administrator account.
     * A new Account object is created each call, but the values never change.
     */
    @Override
    public Account findAdminAccount() {
        return new Account(VALID_USERNAME, VALID_PASSWORD);
    }
}
