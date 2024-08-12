package view.signup.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.user_account.UserAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountSignupViewTest {

    private UserAccountSignupViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountSignupController controller;
    private UserAccountSignupView userAccountSignupView;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountSignupViewModel();
        viewManager = new ViewManagerModel();
        controller = new UserAccountSignupController(null); // Assuming null for simplicity
        userAccountSignupView = new UserAccountSignupView(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountSignupViewInitialization() {
        assertNotNull(userAccountSignupView);
        assertEquals("User Account Signup", userAccountSignupView.getTitle()); // Check title
        assertEquals(JFrame.EXIT_ON_CLOSE, userAccountSignupView.getDefaultCloseOperation()); // Check close operation
    }

    @Test
    void testUserAccountSignupPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) userAccountSignupView.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(UserAccountSignupPanel.class, contentPane.getClass());
    }
}

