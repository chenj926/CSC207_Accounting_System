package use_case;

import entity.UserAccount;
import data_access.LogoutDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Logout.LogoutInputData;
import use_case.Logout.LogoutInteractor;
import use_case.Logout.LogoutOutputBoundary;
import use_case.Logout.LogoutOutputData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutInteractorTest {

    private LogoutDataAccessInterface userDataAccessObject;
    private SimplePresenter presenter;
    private LogoutInteractor logoutInteractor;

    @BeforeEach
    public void setUp() {
        userDataAccessObject = new InMemoryLogoutDataAccess();
        presenter = new SimplePresenter();
        logoutInteractor = new LogoutInteractor(userDataAccessObject, presenter);
    }

    @Test
    public void testExecuteLogout() {
        String userId = "user123";
        UserAccount user = new UserAccount("testUser", "password", userId);
        ((InMemoryLogoutDataAccess) userDataAccessObject).addUser(user);
        LogoutInputData inputData = new LogoutInputData(userId);

        logoutInteractor.execute(inputData);

        assertTrue(presenter.isSuccess());
        assertEquals(userId, presenter.getData().getFail());
        assertTrue(((InMemoryLogoutDataAccess) userDataAccessObject).isLoggedOut(userId));
    }

    private static class InMemoryLogoutDataAccess implements LogoutDataAccessInterface {
        private final Map<String, UserAccount> userDatabase = new HashMap<>();
        private final Set<String> loggedOutUsers = new HashSet<>();

        @Override
        public UserAccount getById(String identification) {
            return userDatabase.get(identification);
        }


        public void logout(UserAccount user) {
            loggedOutUsers.add(user.getIdentification());
        }

        public void addUser(UserAccount user) {
            userDatabase.put(user.getIdentification(), user);
        }

        public boolean isLoggedOut(String userId) {
            return loggedOutUsers.contains(userId);
        }
    }

    private static class SimplePresenter implements LogoutOutputBoundary {
        private LogoutOutputData data;
        private boolean success;

        @Override
        public void prepareSuccessView(LogoutOutputData data) {
            this.data = data;
            this.success = true;
        }

        @Override
        public void prepareFailView(String error) {

        }

        public boolean isSuccess() {
            return success;
        }

        public LogoutOutputData getData() {
            return data;
        }
    }
}

