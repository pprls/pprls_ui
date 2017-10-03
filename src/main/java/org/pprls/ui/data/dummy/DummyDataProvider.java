package org.pprls.ui.data.dummy;

import org.pprls.ui.data.DataProvider;
import org.pprls.ui.domain.User;

/**
 * A dummy implementation for the backend API.
 */
public class DummyDataProvider implements DataProvider {

    /**
     * Initialize the data for this application.
     */
    public DummyDataProvider() {
    }



    @Override
    public User authenticate(String userName, String password) {
        User user = new User();
        user.setFirstName("Κωνσταντίνος");
        user.setLastName("Αποστόλου");
        user.setRole("admin");
        String email = user.getFirstName().toLowerCase() + "."
                + user.getLastName().toLowerCase() + "@"
                + "ΟΠΕΚΕΠΕ".toLowerCase() + ".com";
        user.setEmail(email.replaceAll(" ", ""));
        user.setLocation("Ιωάννινα");
        user.setBio("Quis aute iure reprehenderit in voluptate velit esse."
                + "Cras mattis iudicium purus sit amet fermentum.");
        return user;
    }



}
