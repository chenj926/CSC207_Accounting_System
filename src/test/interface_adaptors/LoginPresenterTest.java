package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.LogInOutputBoundary;
import use_case.LogInOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    @BeforeEach
    void setUp() {
        loginViewModel = new LoginViewModelMock();
        viewManagerModel = new ViewManagerModelMock();
        loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel);
    }

    @Test
    void prepareSuccessView() {
        // Arrange
        String username = "testuser";
        LogInOutputData outputData = new LogInOutputData(username, true);
        LoginState expectedLoginState = new LoginState();
        expectedLoginState.setUsername(username);

        // Act
        loginPresenter.prepareSuccessView(outputData);

        // Assert
        assertEquals(expectedLoginState.getUsername(), ((LoginViewModelMock) loginViewModel).getState().getUsername());
        assertTrue(((LoginViewModelMock) loginViewModel).isPropertyChangedFired());
        assertEquals(loginViewModel.getViewName(), ((ViewManagerModelMock) viewManagerModel).getActiveViewName());
    }

    @Test
    void prepareFailView() {
        // Arrange
        String error = "Invalid credentials";
        LoginState expectedLoginState = new LoginState();
        expectedLoginState.setUsernameError(error);

        // Act
        loginPresenter.prepareFailView(error);

        // Assert
        assertEquals(expectedLoginState.getUsernameError(), ((LoginViewModelMock) loginViewModel).getState().getUsernameError());
        assertTrue(((LoginViewModelMock) loginViewModel).isPropertyChangedFired());
    }

    private class LoginViewModelMock extends LoginViewModel {
        private LoginState state;
        private boolean propertyChangedFired;

        public LoginViewModelMock() {
            this.state = new LoginState();
            this.propertyChangedFired = false;
        }

        @Override
        public LoginState getState() {
            return this.state;
        }

        @Override
        public void setState(LoginState state) {
            this.state = state;
        }

        @Override
        public void firePropertyChanged() {
            this.propertyChangedFired = true;
        }

        public boolean isPropertyChangedFired() {
            return this.propertyChangedFired;
        }

        @Override
        public String getViewName() {
            return "login";
        }
    }

    private class ViewManagerModelMock extends ViewManagerModel {
        private String activeViewName;

        @Override
        public void setActiveViewName(String viewName) {
            this.activeViewName = viewName;
        }

        @Override
        public void setActiveView(String viewName) {

        }

        public String getActiveViewName() {
            return this.activeViewName;
        }
    }
}