package interface_adaptors.login.shared_account;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.shared_account.SharedAccountLoginOutputData;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountLoginPresenterTest {

    private SharedAccountLoginPresenter presenter;
    private ViewManagerModel viewManagerModel;
    private SharedAccountLoginViewModel sharedAccountLoginViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel(); // Use a real instance instead of a mock
        sharedAccountLoginViewModel = new SharedAccountLoginViewModel();
        presenter = new SharedAccountLoginPresenter(viewManagerModel, sharedAccountLoginViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        SharedAccountLoginOutputData outputData = new SharedAccountLoginOutputData("shared123", true);

        presenter.prepareSuccessView(outputData);

        SharedAccountLoginState state = sharedAccountLoginViewModel.getState();
        assertEquals("shared123", state.getIdentification());
        assertEquals("Successfully Logged In to Shared Account!!!", state.getSuccessMsg());

        assertEquals("shared123", viewManagerModel.getUserId());
        assertEquals(sharedAccountLoginViewModel.getViewName(), viewManagerModel.getActiveViewName());
        assertEquals("Shared Account Homepage Two", viewManagerModel.getActiveViewName());
    }
}

