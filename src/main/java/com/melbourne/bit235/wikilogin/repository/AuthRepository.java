package com.melbourne.bit235.wikilogin.repository;

import com.melbourne.bit235.wikilogin.model.Account; // The account value object returned to the service.

/**
 * Data-access contract for administrator credentials.
 */
public interface AuthRepository {

    /**
     * Supplies the one valid administrator account.
     *
     * @return the hard coded account used by this assessment
     */
    Account findAdminAccount();
}
