package data_access;
import entity.UserAccount;


public interface LogoutDataAccessInterface {
    UserAccount getById(String identification);
    void logout(UserAccount user);
}
