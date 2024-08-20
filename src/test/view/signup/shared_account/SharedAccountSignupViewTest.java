package view.signup.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupController;
import interface_adaptors.signup.shared_account.SharedAccountSignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountSignupViewTest {

    private SharedAccountSignupViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountSignupController controller;
    private SharedAccountSignupView sharedAccountSignupView;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountSignupViewModel();
        viewManager = new ViewManagerModel();
        controller = new SharedAccountSignupController(null); // Assuming null for simplicity
        sharedAccountSignupView = new SharedAccountSignupView(viewModel, controller, viewManager);
    }

    @Test
    void testSharedAccountSignupViewInitialization() {
        assertNotNull(sharedAccountSignupView);
        assertEquals("Shared Account Sign Up", sharedAccountSignupView.getTitle()); // Check title
        assertEquals(JFrame.EXIT_ON_CLOSE, sharedAccountSignupView.getDefaultCloseOperation()); // Check close operation
    }

    @Test
    void testSharedAccountSignupPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) sharedAccountSignupView.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(SharedAccountSignupPanel.class, contentPane.getClass());
    }
}

