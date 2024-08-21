package use_case.Login.UserAccount;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import data_access.authentication.user_account.InMemoryLoginoutDataAccessObject;
import data_access.authentication.user_account.UserAccountLoginDataAccessInterface;

import entity.account.user_account.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.user_account.*;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInteractor;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAccountLoginInteractorTest {

    private UserAccountLoginDataAccessInterface userAccountDataAccessObject;
    private SimplePresenter presenter;
    private UserAccountLoginInteractor loginInteractor;

    @BeforeEach
    public void setUp() {
        userAccountDataAccessObject = new InMemoryUserAccountLoginDataAccess();
        presenter = new SimplePresenter();
        loginInteractor = new UserAccountLoginInteractor(userAccountDataAccessObject, presenter);
    }

    @Test
    public void testUserAccountNotFound() {
        String userAccountId = "nonexistentAccount";
        String userPassword = "password123";
        UserAccountLoginInputData inputData = new UserAccountLoginInputData(userAccountId, userPassword);

        loginInteractor.execute(inputData);

        assertEquals("User not found", presenter.getMessage());
    }

    @Test
    public void testIncorrectPassword() {
        String userAccountId = "userAccount1";
        String userPassword = "wrongPassword";

        UserAccount userAccount = new UserAccount("username", "correctPassword", userAccountId);
        ((InMemoryUserAccountLoginDataAccess) userAccountDataAccessObject).addAccount(userAccount);
        UserAccountLoginInputData inputData = new UserAccountLoginInputData(userAccountId, userPassword);

        loginInteractor.execute(inputData);

        assertEquals("Incorrect Password!", presenter.getMessage());
    }

    @Test
    public void testSuccessfulLogin() {
        String userAccountId = "userAccount1";
        String userPassword = "correctPassword";

        UserAccount userAccount = new UserAccount("username", userPassword, userAccountId);
        ((InMemoryUserAccountLoginDataAccess) userAccountDataAccessObject).addAccount(userAccount);
        UserAccountLoginInputData inputData = new UserAccountLoginInputData(userAccountId, userPassword);

        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getUserAccountDataAccessObject();
        UserAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UserAccountUpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        UserAccountLoginMediator mediator = new UserAccountLoginMediator(loginInteractor,
                updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        loginInteractor.setMediator(mediator);
        loginInteractor.execute(inputData);

        assertTrue(presenter.isSuccess());
        assertEquals(userAccountId, presenter.getData().getIdentification());
    }

    @Test
    public void testEmptyIdentification() {
        String userAccountId = "";
        String userPassword = "password123";
        UserAccountLoginInputData inputData = new UserAccountLoginInputData(userAccountId, userPassword);

        loginInteractor.execute(inputData);

        assertEquals("Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testEmptyPassword() {
        String userAccountId = "userAccount1";
        String userPassword = "";
        UserAccountLoginInputData inputData = new UserAccountLoginInputData(userAccountId, userPassword);

        loginInteractor.execute(inputData);

        assertEquals("Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testEmptyIdentificationAndPassword() {
        String userAccountId = "";
        String userPassword = "";
        UserAccountLoginInputData inputData = new UserAccountLoginInputData(userAccountId, userPassword);

        loginInteractor.execute(inputData);

        assertEquals("Identification AND Password can not be empty!", presenter.getMessage());
    }

    private static class InMemoryUserAccountLoginDataAccess implements UserAccountLoginDataAccessInterface {
        private final Map<String, UserAccount> userAccountDatabase = new HashMap<>();

        @Override
        public boolean existById(String identification) {
            return userAccountDatabase.containsKey(identification);
        }

        @Override
        public UserAccount getById(String identification) {
            return userAccountDatabase.get(identification);
        }

        @Override
        public boolean login(UserAccount sharedAccount) {
            return sharedAccount != null && userAccountDatabase.containsKey(sharedAccount.getIdentification());
        }

        public void addAccount(UserAccount sharedAccount) {
            userAccountDatabase.put(sharedAccount.getIdentification(), sharedAccount);
        }
    }

    private static class SimplePresenter implements UserAcountLoginOutputBoundary {
        private String message;
        private UserAccountLoginOutputData data;
        private boolean success;

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }

        @Override
        public void prepareSuccessView(UserAccountLoginOutputData data) {
            this.data = data;
            this.success = true;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public UserAccountLoginOutputData getData() {
            return data;
        }
    }
}
