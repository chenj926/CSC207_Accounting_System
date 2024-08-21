package use_case.Login.SharedAccount;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import data_access.authentication.shared_account.SharedAccountLoginDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInteractor;
import use_case.login.shared_account.*;
import use_case.update_periodic_at_login.shared_account.SharedAccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.shared_account.SharedAccountUpdatePeriodicAtLoginInteractor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SharedAccountLoginInteractorTest {

    private SharedAccountLoginDataAccessInterface sharedAccountDataAccessObject;
    private SimplePresenter presenter;
    private SharedAccountLoginInteractor loginInteractor;

    @BeforeEach
    public void setUp() {
        sharedAccountDataAccessObject = new InMemorySharedAccountLoginDataAccess();
        presenter = new SimplePresenter();
        loginInteractor = new SharedAccountLoginInteractor(sharedAccountDataAccessObject, presenter);
    }

    @Test
    public void testSharedAccountNotFound() {
        String sharedAccountId = "nonexistentAccount";
        String sharedPassword = "password123";
        SharedAccountLoginInputData inputData = new SharedAccountLoginInputData(sharedAccountId, sharedPassword);

        loginInteractor.execute(inputData);

        assertEquals("Shared Account not found", presenter.getMessage());
    }

    @Test
    public void testIncorrectPassword() {
        String sharedAccountId = "sharedAccount1";
        String sharedPassword = "wrongPassword";
        Set<String> userIds = new HashSet<>();
        userIds.add("id01");
        userIds.add("id02");
        SharedAccount sharedAccount = new SharedAccount(sharedAccountId, userIds, "correctPassword");
        ((InMemorySharedAccountLoginDataAccess) sharedAccountDataAccessObject).addAccount(sharedAccount);
        SharedAccountLoginInputData inputData = new SharedAccountLoginInputData(sharedAccountId, sharedPassword);

        loginInteractor.execute(inputData);

        assertEquals("Incorrect Password!", presenter.getMessage());
    }

    @Test
    public void testSuccessfulLogin() {
        String sharedAccountId = "sharedAccount1";
        String sharedPassword = "correctPassword";
        Set<String> userIds = new HashSet<>();
        userIds.add("id01");
        userIds.add("id02");
        SharedAccount sharedAccount = new SharedAccount(sharedAccountId, userIds, "correctPassword");
        ((InMemorySharedAccountLoginDataAccess) sharedAccountDataAccessObject).addAccount(sharedAccount);
        SharedAccountLoginInputData inputData = new SharedAccountLoginInputData(sharedAccountId, sharedPassword);

        SharedAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new SharedAccountUpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        SharedAccountLoginMediator mediator = new SharedAccountLoginMediator(loginInteractor,
                updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        loginInteractor.setMediator(mediator);
        loginInteractor.execute(inputData);

        assertTrue(presenter.isSuccess());
        assertEquals(sharedAccountId, presenter.getData().getIdentification());
    }

    @Test
    public void testEmptyIdentification() {
        String sharedAccountId = "";
        String sharedPassword = "password123";
        SharedAccountLoginInputData inputData = new SharedAccountLoginInputData(sharedAccountId, sharedPassword);

        loginInteractor.execute(inputData);

        assertEquals("Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testEmptyPassword() {
        String sharedAccountId = "sharedAccount1";
        String sharedPassword = "";
        SharedAccountLoginInputData inputData = new SharedAccountLoginInputData(sharedAccountId, sharedPassword);

        loginInteractor.execute(inputData);

        assertEquals("Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testEmptyIdentificationAndPassword() {
        String sharedAccountId = "";
        String sharedPassword = "";
        SharedAccountLoginInputData inputData = new SharedAccountLoginInputData(sharedAccountId, sharedPassword);

        loginInteractor.execute(inputData);

        assertEquals("Identification AND Password can not be empty!", presenter.getMessage());
    }

    private static class InMemorySharedAccountLoginDataAccess implements SharedAccountLoginDataAccessInterface {
        private final Map<String, SharedAccount> sharedAccountDatabase = new HashMap<>();

        @Override
        public boolean existById(String identification) {
            return sharedAccountDatabase.containsKey(identification);
        }

        @Override
        public SharedAccount getById(String identification) {
            return sharedAccountDatabase.get(identification);
        }

        @Override
        public boolean login(SharedAccount sharedAccount) {
            return sharedAccount != null && sharedAccountDatabase.containsKey(sharedAccount.getIdentification());
        }

        public void addAccount(SharedAccount sharedAccount) {
            sharedAccountDatabase.put(sharedAccount.getIdentification(), sharedAccount);
        }
    }

    private static class SimplePresenter implements SharedAccountLoginOutputBoundary {
        private String message;
        private SharedAccountLoginOutputData data;
        private boolean success;

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }

        @Override
        public void prepareSuccessView(SharedAccountLoginOutputData data) {
            this.data = data;
            this.success = true;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public SharedAccountLoginOutputData getData() {
            return data;
        }
    }
}
