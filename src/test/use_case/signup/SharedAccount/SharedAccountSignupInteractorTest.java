package use_case.signup.SharedAccount;

import entity.account.shared_account.SharedAccount;
import entity.account.AccountFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import entity.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.shared_account.SharedAccountSignupInputData;
import use_case.signup.shared_account.SharedAccountSignupInteractor;
import use_case.signup.shared_account.SharedAccountSignupOutputBoundary;
import use_case.signup.shared_account.SharedAccountSignupOutputData;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SharedAccountSignupInteractorTest {

    private SharedAccountSignupDataAccessInterface sharedAccountSignupDataAccess;
    private SharedAccountDataAccessInterface sharedAccountDataAccessObject;
    private SimplePresenter presenter;
    private AccountFactory accountFactory;
    private SharedAccountSignupInteractor signupInteractor;

    @BeforeEach
    public void setUp() {
        sharedAccountSignupDataAccess = new InMemorySharedAccountSignupDataAccess();
        sharedAccountDataAccessObject = new InMemorySharedAccountDataAccess();
        presenter = new SimplePresenter();
        accountFactory = new AccountFactory();
        signupInteractor = new SharedAccountSignupInteractor(sharedAccountSignupDataAccess, sharedAccountDataAccessObject, presenter, accountFactory);
    }

    @Test
    public void testSharedAccountAlreadyExists() {
        String sharedAccountId = "existingSharedAccount";
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");
        userIds.add("user2");

        SharedAccount existingSharedAccount = new SharedAccount(sharedAccountId, userIds, "sharedPassword");
        ((InMemorySharedAccountDataAccess) sharedAccountDataAccessObject).addAccount(existingSharedAccount);

        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData(sharedAccountId, "sharedPassword", userIds);

        signupInteractor.execute(inputData);

        assertEquals("A shared account with this ID already exists!", presenter.getMessage());
    }

    @Test
    public void testUserIdsDoNotExist() {
        String sharedAccountId = "newSharedAccount";
        Set<String> userIds = new HashSet<>();
        userIds.add("user1"); // This user does not exist in the system
        userIds.add("user2"); // This user does not exist in the system

        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData(sharedAccountId, "sharedPassword", userIds);

        signupInteractor.execute(inputData);

        assertEquals("All user accounts must exist before creating a shared account!", presenter.getMessage());
    }

    @Test
    public void testInvalidSharedAccountInputData() {
        String sharedAccountId = "";
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");
        userIds.add("user2");

        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData(sharedAccountId, "", userIds);

        signupInteractor.execute(inputData);

        assertEquals("All fields must be filled out!", presenter.getMessage());
    }

    @Test
    public void testSharedAccountCreatedSuccessfully() {
        String sharedAccountId = "newSharedAccount";
        Set<String> userIds = new HashSet<>();
        userIds.add("id0");
        userIds.add("id1");

        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData(sharedAccountId, "sharedPassword", userIds);

        signupInteractor.execute(inputData);

        assertTrue(presenter.isSuccess());
        assertEquals(sharedAccountId, presenter.getData().getSharedAccountId());
        assertEquals(userIds, presenter.getData().getUserIds());
    }

    private static class InMemorySharedAccountSignupDataAccess implements SharedAccountSignupDataAccessInterface {
        private final Set<String> existingUserIds = new HashSet<>();

        @Override
        public boolean existById(String identification) {
            return existingUserIds.contains(identification);
        }

        @Override
        public SharedAccount getById(String identification) {
            return null;
        }

        @Override
        public void save(SharedAccount user) {

        }

    }

    private static class InMemorySharedAccountDataAccess implements SharedAccountDataAccessInterface {
        private final Set<SharedAccount> sharedAccounts = new HashSet<>();

        @Override
        public boolean existById(String identification) {
            return sharedAccounts.stream().anyMatch(account -> account.getIdentification().equals(identification));
        }

        @Override
        public SharedAccount getById(String identification) {
            return sharedAccounts.stream().filter(account -> account.getIdentification().equals(identification)).findFirst().orElse(null);
        }

        @Override
        public void update(SharedAccount account) {

        }

        @Override
        public void deleteById(String identifier) {

        }

        @Override
        public void save(SharedAccount account) {
            sharedAccounts.add(account);
        }

        @Override
        public void saveTransaction(SharedAccountOneTimeTransactionOutputData oneTimeOutputData, SharedAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {

        }

        @Override
        public List<Transaction> readTransactions(String userId) {
            return List.of();
        }

        public void addAccount(SharedAccount account) {
            sharedAccounts.add(account);
        }
    }

    private static class SimplePresenter implements SharedAccountSignupOutputBoundary {
        private String message;
        private SharedAccountSignupOutputData data;
        private boolean success;

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }

        @Override
        public void prepareSuccessView(SharedAccountSignupOutputData data) {
            this.data = data;
            this.success = true;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public SharedAccountSignupOutputData getData() {
            return data;
        }
    }
}
