package interface_adaptors.login.user_account;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.user_account.UserAccountLoginOutputData;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountLoginPresenterTest {

    private UserAccountLoginPresenter presenter;
    private ViewManagerModel viewManagerModel;
    private UserAccountLoginViewModel userAccountLoginViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel(); // Use a real instance instead of a mock
        userAccountLoginViewModel = new UserAccountLoginViewModel();
        presenter = new UserAccountLoginPresenter(viewManagerModel, userAccountLoginViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        UserAccountLoginOutputData outputData = new UserAccountLoginOutputData("user123", true);

        presenter.prepareSuccessView(outputData);

        UserAccountLoginState state = userAccountLoginViewModel.getState();
        assertEquals("user123", state.getIdentification());
        assertEquals("Successfully Logged In!!!", state.getSuccessMsg());

        assertEquals("user123", viewManagerModel.getUserId());
        assertEquals(userAccountLoginViewModel.getViewName(), viewManagerModel.getActiveViewName());
        assertEquals("Homepage Two", viewManagerModel.getActiveViewName());
    }
}

