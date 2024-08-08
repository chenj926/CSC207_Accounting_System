package use_case;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import data_access.authentication.LoginDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.*;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInteractor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginInteractorTest {

    private LoginDataAccessInterface userDataAccessObject;
    private SimplePresenter presenter;
    private LoginInteractor loginInteractor;

    @BeforeEach
    public void setUp() {
        userDataAccessObject = new InMemoryLoginDataAccess();
        presenter = new SimplePresenter();
        loginInteractor = new LoginInteractor(userDataAccessObject, presenter);
    }

    @Test
    public void testUserNotFound() {
        String password = "password123";
        String userId = "nonexistentUser";
        LoginInputData inputData = new LoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("User not found", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

//    @Test
//    public void testUserFoundAndLoggedIn() {
//        String username = "testUser";
//        String password = "password123";
//        String userId = "existentUser";
//        UserAccount user = new UserAccount(username, password, userId);
//        ((InMemoryLoginDataAccess) userDataAccessObject).addUser(user);
//        LoginInputData inputData = new LoginInputData(password, userId);
//        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getPeriodicTransactionDAO();
//        user.setLastLoginDate(LocalDate.now());
//        periodicTransactionDataAccessObject.save(user);
//        UpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);
//
//        LoginMediator loginMediator = new LoginMediator(loginInteractor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
//        loginInteractor.setMediator(loginMediator);
//
//
//        loginInteractor.execute(inputData);

//        assertTrue(presenter.isSuccess());
//        assertEquals(userId, presenter.getData().getIdentification());
//    }

    @Test
    public void testEmptyPassword() {
        String password = "";
        String userId = "existentUser";
        LoginInputData inputData = new LoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("Password can not be empty!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testEmptyIdentification() {
        String password = "password123";
        String userId = "";
        LoginInputData inputData = new LoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("Identification can not be empty!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testEmptyPasswordAndIdentification() {
        String password = "";
        String userId = "";
        LoginInputData inputData = new LoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("Identification AND Password can not be empty!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testIncorrectPassword() {
        String username = "testUser";
        String password = "password123";
        String userId = "existentUser";
        UserAccount user = new UserAccount(username, password, userId);
        ((InMemoryLoginDataAccess) userDataAccessObject).addUser(user);
        LoginInputData inputData = new LoginInputData("wrongPassword", userId);

        loginInteractor.execute(inputData);

        assertEquals("Incorrect Password!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testLoginFailure() {
        String username = "testUser";
        String password = "password123";
        String userId = "existentUser";
        UserAccount user = new UserAccount(username, password, userId);
        ((InMemoryLoginDataAccess) userDataAccessObject).addUser(user);
        LoginInputData inputData = new LoginInputData(password, userId);

        ((InMemoryLoginDataAccess) userDataAccessObject).setLoginSuccess(false);

        loginInteractor.execute(inputData);

        assertEquals("Incorrect Password!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    private static class InMemoryLoginDataAccess implements LoginDataAccessInterface {
        private final Map<String, UserAccount> userDatabase = new HashMap<>();
        private boolean loginSuccess = true;

        @Override
        public boolean existById(String identification) {
            return userDatabase.containsKey(identification);
        }

        @Override
        public UserAccount getById(String identification) {
            return userDatabase.get(identification);
        }

        @Override
        public boolean login(UserAccount userAccount) {
            return loginSuccess;
        }

        public void addUser(UserAccount userAccount) {
            userDatabase.put(userAccount.getIdentification(), userAccount);
        }

        public void setLoginSuccess(boolean success) {
            this.loginSuccess = success;
        }
    }

    private static class SimplePresenter implements LoginOutputBoundary {
        private String message;
        private LoginOutputData data;
        private boolean success;

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }

        @Override
        public void prepareSuccessView(LoginOutputData data) {
            this.data = data;
            this.success = true;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public LoginOutputData getData() {
            return data;
        }
    }
}





