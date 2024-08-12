package view.login.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.shared_account.SharedAccountLoginController;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountLoginViewTest {

    private SharedAccountLoginViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountLoginController controller;
    private SharedAccountLoginView sharedAccountLoginView;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountLoginViewModel();
        viewManager = new ViewManagerModel();
        controller = new SharedAccountLoginController(null); // Assuming null for simplicity
        sharedAccountLoginView = new SharedAccountLoginView(viewModel, controller, viewManager);
    }

    @Test
    void testSharedAccountLoginViewInitialization() {
        assertNotNull(sharedAccountLoginView);
        assertEquals("SHARED ACCOUNT LOGIN", sharedAccountLoginView.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, sharedAccountLoginView.getDefaultCloseOperation());
    }

    @Test
    void testSharedAccountLoginPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) sharedAccountLoginView.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(SharedAccountLoginPanel.class, contentPane.getClass());
    }
}

