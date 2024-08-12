package view.login.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.user_account.UserAccountLoginController;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountLoginViewTest {

    private UserAccountLoginViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountLoginController controller;
    private UserAccountLoginView userAccountLoginView;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountLoginViewModel();
        viewManager = new ViewManagerModel();
        controller = new UserAccountLoginController(null); // Assuming null for simplicity
        userAccountLoginView = new UserAccountLoginView(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountLoginViewInitialization() {
        assertNotNull(userAccountLoginView);
        assertEquals("ACCOUNT LOGIN", userAccountLoginView.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, userAccountLoginView.getDefaultCloseOperation());
    }

    @Test
    void testUserAccountLoginPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) userAccountLoginView.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(UserAccountLoginPanel.class, contentPane.getClass());
    }
}

