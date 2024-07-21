package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.SignupOutputData;

class SignupPresenterTest {

    @Test
    void testPrepareSuccessView() {
        // Arrange
        String username = "testUsername";
        SignupOutputData successOutputData = new SignupOutputData(username, false);
        SignupViewModel mockSignupViewModel = new MockSignupViewModel();
        ViewManagerModel mockViewManagerModel = new MockViewManagerModel() {
            @Override
            public void setActiveView(String viewName) {

            }
        };
        SignupPresenter signupPresenter = new SignupPresenter(mockViewManagerModel, mockSignupViewModel);

        // Act
        signupPresenter.prepareSuccessView(successOutputData);

        // Assert
        Assertions.assertEquals(username, ((MockSignupViewModel) mockSignupViewModel).getState().getUsername());
        Assertions.assertEquals(((MockSignupViewModel) mockSignupViewModel).getViewName(), mockViewManagerModel.getActiveViewName());
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String error = "testError";
        SignupViewModel mockSignupViewModel = new MockSignupViewModel();
        SignupPresenter signupPresenter = new SignupPresenter(new MockViewManagerModel() {
            @Override
            public void setActiveView(String viewName) {

            }
        }, mockSignupViewModel);

        // Act
        signupPresenter.prepareFailView(error);

        // Assert
        Assertions.assertEquals(error, ((MockSignupViewModel) mockSignupViewModel).getState().getUsernameError());
        Assertions.assertTrue(((MockSignupViewModel) mockSignupViewModel).isPropertyChangedFired());
    }

    private static class MockSignupViewModel extends SignupViewModel {
        private SignupState state;
        private boolean propertyChangedFired;

        public MockSignupViewModel() {
            this.state = new SignupState();
        }

        @Override
        public SignupState getState() {
            return this.state;
        }

        @Override
        public void setState(SignupState state) {
            this.state = state;
        }

        @Override
        public void firePropertyChanged() {
            this.propertyChangedFired = true;
        }

        @Override
        public String getViewName() {
            return "signup";
        }

        public boolean isPropertyChangedFired() {
            return this.propertyChangedFired;
        }
    }

    private static abstract class MockViewManagerModel extends ViewManagerModel {
        private String activeViewName;

        @Override
        public void setActiveViewName(String viewName) {
            this.activeViewName = viewName;
        }

        public String getActiveViewName() {
            return this.activeViewName;
        }
    }
}