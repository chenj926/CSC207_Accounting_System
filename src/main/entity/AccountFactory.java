package entity;

public class AccountFactory {
    public UserAccount createUserAccount(String username, String password, String identification) {
        return new UserAccount(username, password, identification);
    }

    public SharedAccount createSharedAccount(String identification) {
        return new SharedAccount(identification);
    }
}