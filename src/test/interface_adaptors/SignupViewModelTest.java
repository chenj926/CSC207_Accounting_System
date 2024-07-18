package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SignupViewModelTest {

    private SignupViewModel signupViewModel;

    @BeforeEach
    void setUp() {
        signupViewModel = new SignupViewModel();
    }

    @Test
    void testGetters() {
        Assertions.assertEquals("Sign Up View", signupViewModel.getTitleLabel());
        Assertions.assertEquals("Set username", signupViewModel.getUsernameLabel());
        Assertions.assertEquals("Set password", signupViewModel.getPasswordLabel());
        Assertions.assertEquals("Set identification", signupViewModel.getID_LABEL());
        Assertions.assertEquals("Sign up", signupViewModel.getSignupButtonLabel());
        Assertions.assertEquals("Cancel", signupViewModel.getCancelButtonLabel());
    }

}