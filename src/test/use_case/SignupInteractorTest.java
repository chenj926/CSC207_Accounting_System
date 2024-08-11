package use_case;

import entity.account.user_account.UserAccount;
import entity.account.AccountFactory;
import data_access.authentication.user_account.UserSignupDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.user_account.UserAccountSignupInputData;
import use_case.signup.user_account.UserAccountSignupInteractor;
import use_case.signup.user_account.UserAccountSignupOutputBoundary;
import use_case.signup.user_account.UserAccountSignupOutputData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupInteractorTest {

    private UserSignupDataAccessInterface userDataAccessObject;
    private SimplePresenter presenter;
    private AccountFactory accountFactory;
    private UserAccountSignupInteractor signupInteractor;

    @BeforeEach
    public void setUp() {
        userDataAccessObject = new InMemoryUserSignupDataAccess();
        presenter = new SimplePresenter();
        accountFactory = new AccountFactory();
        signupInteractor = new UserAccountSignupInteractor(userDataAccessObject, presenter, accountFactory);
    }

    @Test
    public void testUserAlreadyExists() {
        String username = "testUser";
        String password = "password123";
        String userId = "existentUser";
        UserAccount user = new UserAccount(username, password, userId);
        ((InMemoryUserSignupDataAccess) userDataAccessObject).addUser(user);
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("User already exist!!!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsername() {
        String username = "";
        String password = "password123";
        String userId = "newUser";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidPassword() {
        String username = "testUser";
        String password = "";
        String userId = "newUser";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidIdentification() {
        String username = "testUser";
        String password = "1234";
        String userId = "";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsernameAndPassword() {
        String username = "";
        String password = "";
        String userId = "newUser";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username AND Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsernameAndIdentification() {
        String username = "";
        String password = "1234";
        String userId = "";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidPasswordAndIdentification() {
        String username = "testUser";
        String password = "";
        String userId = "";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Password AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsernameAndPasswordAndIdentification() {
        String username = "";
        String password = "";
        String userId = "";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username AND Password AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testUserCreatedSuccessfully() {

        String username = "testUser";
        String password = "password123";
        String userId = "newUser";
        UserAccountSignupInputData inputData = new UserAccountSignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertTrue(presenter.isSuccess());
        assertEquals(username, presenter.getData().getUsername());
    }

    private static class InMemoryUserSignupDataAccess implements UserSignupDataAccessInterface {
        private final Map<String, UserAccount> userDatabase = new HashMap<>();

        @Override
        public boolean existById(String identification) {
            return userDatabase.containsKey(identification);
        }

        @Override
        public UserAccount getById(String identification) {
            return null;
        }

        @Override
        public void save(UserAccount user) {
            userDatabase.put(user.getIdentification(), user);
        }

        public void addUser(UserAccount user) {
            userDatabase.put(user.getIdentification(), user);
        }
    }

    private static class SimplePresenter implements UserAccountSignupOutputBoundary {
        private String message;
        private UserAccountSignupOutputData data;
        private boolean success;

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }

        @Override
        public void prepareSuccessView(UserAccountSignupOutputData data) {
            this.data = data;
            this.success = true;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public UserAccountSignupOutputData getData() {
            return data;
        }
    }
}
