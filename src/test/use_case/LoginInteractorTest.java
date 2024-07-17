package use_case;

import entity.AccountFactory;
import entity.UserAccount;
import data_access.LoginDataAccessInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LoginInteractorTest {

    private LoginInteractor loginInteractor;
    private LoginDataAccessInterfaceStub userDataAccessObject;
    private LogInOutputBoundaryStub presenter;
    private AccountFactory accountFactory;

    @Before
    public void setUp() {
        userDataAccessObject = new LoginDataAccessInterfaceStub();
        presenter = new LogInOutputBoundaryStub();
        accountFactory = new AccountFactory();
        loginInteractor = new LoginInteractor(userDataAccessObject, presenter, accountFactory);
    }

    @Test
    public void testExecuteUserNotFound() {
        LoginInputData inputData = new LoginInputData("testUser", "testPass", "testID");

        loginInteractor.execute(inputData);

        assertFalse("Presenter should have failed", presenter.isSuccess());
        assertEquals("User not found", presenter.getMessage());
        assertFalse("Login should not have been called", userDataAccessObject.isLoginCalled());
    }

    @Test
    public void testExecuteUserFound() {
        UserAccount userAccount = new UserAccount("testUser", "testPass", "testID");
        userDataAccessObject.addUser(userAccount);

        LoginInputData inputData = new LoginInputData("testUser", "testPass", "testID");

        loginInteractor.execute(inputData);

        assertTrue("Presenter should have succeeded", presenter.isSuccess());
        assertNotNull("Output data should not be null", presenter.getOutputData());
        assertTrue(presenter.getOutputData().isSuccess());
        assertTrue("Login should have been called", userDataAccessObject.isLoginCalled());
    }

    // Stub for LoginDataAccessInterface
    class LoginDataAccessInterfaceStub implements LoginDataAccessInterface {
        private Map<String, UserAccount> users = new HashMap<>();
        private boolean loginCalled = false;

        public void addUser(UserAccount user) {
            users.put(user.getIdentification(), user);
        }

        public boolean isLoginCalled() {
            return loginCalled;
        }

        @Override
        public boolean existById(String identification) {
            return users.containsKey(identification);
        }

        @Override
        public UserAccount getById(String identification) {
            return users.get(identification);
        }

        @Override
        public void login(UserAccount userAccount) {
            loginCalled = true;
        }
    }

    // Stub for LogInOutputBoundary
    class LogInOutputBoundaryStub implements LogInOutputBoundary {
        private boolean success;
        private String message;
        private LogInOutputData outputData;

        @Override
        public void prepareSuccessView(LogInOutputData logInOutputData) {
            this.success = true;
            this.outputData = logInOutputData;
        }

        @Override
        public void prepareFailView(String message) {
            this.success = false;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public LogInOutputData getOutputData() {
            return outputData;
        }
    }
}

