package org.pprls.ui.data;


import org.pprls.ui.domain.User;

/**
 * QuickTickets Dashboard backend API.
 */
public interface DataProvider {

    /**
     * @param userName
     * @param password
     * @return Authenticated used.
     */
    User authenticate(String userName, String password);


}
