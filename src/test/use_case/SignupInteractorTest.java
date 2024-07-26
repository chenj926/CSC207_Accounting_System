package use_case;

import entity.account.UserAccount;
import entity.account.AccountFactory;
import data_access.authentication.UserSignupDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputData;
import use_case.transaction.SignupInteractor;
import use_case.transaction.SignupOutputBoundary;
import use_case.transaction.SignupOutputData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupInteractorTest {

    private UserSignupDataAccessInterface userDataAccessObject;
    private SimplePresenter presenter;
    private AccountFactory accountFactory;
    private SignupInteractor signupInteractor;

    @BeforeEach
    public void setUp() {
        userDataAccessObject = new InMemoryUserSignupDataAccess();
        presenter = new SimplePresenter();
        accountFactory = new AccountFactory();
        signupInteractor = new SignupInteractor(userDataAccessObject, presenter, accountFactory);
    }

    @Test
    public void testUserAlreadyExists() {
        String username = "testUser";
        String password = "password123";
        String userId = "existentUser";
        UserAccount user = new UserAccount(username, password, userId);
        ((InMemoryUserSignupDataAccess) userDataAccessObject).addUser(user);
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("User already exist!!!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsername() {
        String username = "";
        String password = "password123";
        String userId = "newUser";
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidPassword() {
        String username = "testUser";
        String password = "";
        String userId = "newUser";
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidIdentification() {
        String username = "testUser";
        String password = "1234";
        String userId = "";
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsernameAndPassword() {
        String username = "";
        String password = "";
        String userId = "newUser";
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username AND Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsernameAndIdentification() {
        String username = "";
        String password = "1234";
        String userId = "";
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidPasswordAndIdentification() {
        String username = "testUser";
        String password = "";
        String userId = "";
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Password AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUsernameAndPasswordAndIdentification() {
        String username = "";
        String password = "";
        String userId = "";
        SignupInputData inputData = new SignupInputData(username, password, userId);

        signupInteractor.execute(inputData);

        assertEquals("Username AND Password AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testUserCreatedSuccessfully() {

        String username = "testUser";
        String password = "password123";
        String userId = "newUser";
        SignupInputData inputData = new SignupInputData(username, password, userId);

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
        public void save(UserAccount user) {
            userDatabase.put(user.getIdentification(), user);
        }

        public void addUser(UserAccount user) {
            userDatabase.put(user.getIdentification(), user);
        }
    }

    private static class SimplePresenter implements SignupOutputBoundary {
        private String message;
        private SignupOutputData data;
        private boolean success;

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }

        @Override
        public void prepareSuccessView(SignupOutputData data) {
            this.data = data;
            this.success = true;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public SignupOutputData getData() {
            return data;
        }
    }
}
