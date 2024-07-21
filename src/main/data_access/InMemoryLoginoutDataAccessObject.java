package data_access;

import entity.*;
import java.util.*;

public class InMemoryLoginoutDataAccessObject extends InMemoryUserAccountDataAccessObject implements LogoutDataAccessInterface, LoginDataAccessInterface {
    private static Map<String, Boolean> userLogin;

    public InMemoryLoginoutDataAccessObject() {
        userLogin = new HashMap<>();
    }

    @Override
    public boolean existById(String identification) {
        return userLogin.containsKey(identification);
    }

    @Override
    public boolean login(UserAccount userAccount) {
        userLogin.put(userAccount.getIdentification(), true);
        return false;
    }

    @Override
    public void logout(UserAccount user) {
        userLogin.put(user.getIdentification(), false);
    }

    @Override
    public UserAccount getById(String identification) {
        return super.getById(identification);
    }
}
