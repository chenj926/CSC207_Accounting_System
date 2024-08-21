package use_case.signup.UserAccount;

import data_access.account.user_account.UserAccountDataAccessInterface;
import data_access.authentication.user_account.UserSignupDataAccessInterface;
import entity.account.AccountFactory;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.user_account.UserAccountSignupInputData;
import use_case.signup.user_account.UserAccountSignupInteractor;
import use_case.signup.user_account.UserAccountSignupOutputBoundary;
import use_case.signup.user_account.UserAccountSignupOutputData;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAccountSignupInteractorTest {
    private UserSignupDataAccessInterface userAccountSignupDataAccess;
    private UserAccountDataAccessInterface userAccountDataAccessObject;
    private UserAccountSignupInteractorTest.SimplePresenter presenter;
    private AccountFactory accountFactory;
    private UserAccountSignupInteractor signupInteractor;

    @BeforeEach
    public void setUp() {
        userAccountSignupDataAccess = new InMemoryUserAccountSignupDataAccess();
        userAccountDataAccessObject = new InMemoryUserAccountDataAccess();
        presenter = new SimplePresenter();
        accountFactory = new AccountFactory();
        signupInteractor = new UserAccountSignupInteractor(userAccountSignupDataAccess, presenter, accountFactory);
    }

    @Test
    public void testInvalidUserAccountInputData() {

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("", "", "");

        signupInteractor.execute(inputData);

        assertEquals("Username AND Password AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUserAccountUsernameAndPasswordInputData() {

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("", "", "identification");

        signupInteractor.execute(inputData);

        assertEquals("Username AND Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUserAccountUsernameAndIdentificationInputData() {

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("", "password", "");

        signupInteractor.execute(inputData);

        assertEquals("Username AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUserAccountPasswordAndIdentificationInputData() {

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("username", "", "");

        signupInteractor.execute(inputData);

        assertEquals("Password AND Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUserAccountUsernameInputData() {

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("", "password", "identification");

        signupInteractor.execute(inputData);

        assertEquals("Username can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUserAccountPasswordInputData() {

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("username", "", "identification");

        signupInteractor.execute(inputData);

        assertEquals("Password can not be empty!", presenter.getMessage());
    }

    @Test
    public void testInvalidUserAccountIdentificationInputData() {

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("username", "password", "");

        signupInteractor.execute(inputData);

        assertEquals("Identification can not be empty!", presenter.getMessage());
    }

    @Test
    public void testUserAccountCreatedSuccessfully() {
        String userAccountId = "newUserAccount";

        UserAccountSignupInputData inputData = new UserAccountSignupInputData("username", "userPassword", userAccountId);

        signupInteractor.execute(inputData);

        assertTrue(presenter.isSuccess());
        assertEquals("username", presenter.getData().getUsername());
    }

    private static class InMemoryUserAccountSignupDataAccess implements UserSignupDataAccessInterface {
        private final Set<String> existingUserIds = new HashSet<>();

        @Override
        public boolean existById(String identification) {
            return existingUserIds.contains(identification);
        }

        @Override
        public UserAccount getById(String identification) {
            return null;
        }

        @Override
        public void save(UserAccount user) {

        }

    }

    private static class InMemoryUserAccountDataAccess implements UserAccountDataAccessInterface {
        private final Set<UserAccount> userAccounts = new HashSet<>();

        @Override
        public boolean existById(String identification) {
            return userAccounts.stream().anyMatch(account -> account.getIdentification().equals(identification));
        }

        @Override
        public UserAccount getById(String identification) {
            return userAccounts.stream().filter(account -> account.getIdentification().equals(identification)).findFirst().orElse(null);
        }

        @Override
        public void update(UserAccount account) {

        }

        @Override
        public void deleteById(String identifier) {

        }

        @Override
        public void save(UserAccount account) {
            userAccounts.add(account);
        }

        @Override
        public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData, UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {

        }

        @Override
        public List<Transaction> readTransactions(String userId) {
            return List.of();
        }

        public void addAccount(UserAccount account) {
            userAccounts.add(account);
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
