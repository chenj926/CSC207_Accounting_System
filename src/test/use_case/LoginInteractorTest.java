package use_case;

import entity.UserAccount;
import entity.AccountFactory;
import data_access.LoginDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private LoginDataAccessInterface userDataAccessObject;
    private SimplePresenter presenter;
    private AccountFactory accountFactory;
    private LoginInteractor loginInteractor;

    @BeforeEach
    void setUp() {
        userDataAccessObject = new SimpleLoginDataAccess();
        presenter = new SimplePresenter();
        accountFactory = new AccountFactory();
        loginInteractor = new LoginInteractor(userDataAccessObject, presenter, accountFactory);
    }

    @Test
    void testUserNotFound() {
        String username = "testUser";
        String password = "password";
        String userId = "nonexistentUser";
        LoginInputData inputData = new LoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("User not found", presenter.getMessage());
    }

    @Test
    void testUserFoundAndLoggedIn() {
        String username = "testUser";
        String password = "password";
        String userId = "existentUser";
        LoginInputData inputData = new LoginInputData(password, userId);
        ((SimpleLoginDataAccess) userDataAccessObject).addUser(new UserAccount(username, password, userId));

        loginInteractor.execute(inputData);

        assertTrue(presenter.isSuccess());
        assertEquals(username, presenter.getData().getUsername());
    }

    private static class SimpleLoginDataAccess implements LoginDataAccessInterface {
        private final Map<String, UserAccount> userDatabase = new HashMap<>();

        @Override
        public boolean existById(String identification) {
            return userDatabase.containsKey(identification);
        }

        @Override
        public UserAccount getById(String identification) {
            return userDatabase.get(identification);
        }

        @Override
        public void login(UserAccount userAccount) {
        }

        public void addUser(UserAccount userAccount) {
            userDatabase.put(userAccount.getIdentification(), userAccount);
        }
    }

    private static class SimplePresenter implements LogInOutputBoundary {
        private String message;
        private LogInOutputData data;

        @Override
        public void prepareFailView(String message) {
            this.message = message;
        }

        @Override
        public void prepareSuccessView(LogInOutputData data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return data != null;
        }

        public LogInOutputData getData() {
            return data;
        }
    }
}




