package data_access.authentication.shared_account;

import data_access.account.shared_account.InMemorySharedAccountDataAccessObject;
import entity.account.shared_account.SharedAccount;

import java.util.HashMap;
import java.util.Map;

public class InMemorySharedAccountLoginDataAccessObject extends InMemorySharedAccountDataAccessObject implements SharedAccountLoginDataAccessInterface {
    private static Map<String, Boolean> sharedAccountUserLogin;

    public InMemorySharedAccountLoginDataAccessObject() {
        this.sharedAccountUserLogin = new HashMap<>();
    }

    @Override
    public boolean existById(String identification) {
        return this.sharedAccountUserLogin.containsKey(identification);
    }

    @Override
    public boolean login(SharedAccount account) {
        String identifier = account.getIdentification();
        if (existById(identifier)) {
            this.sharedAccountUserLogin.put(identifier, true); // Mark the account as logged in
            return true;
        }
        return false;
    }

    @Override
    public SharedAccount getById(String identification) {
        return super.getById(identification);
    }
}
