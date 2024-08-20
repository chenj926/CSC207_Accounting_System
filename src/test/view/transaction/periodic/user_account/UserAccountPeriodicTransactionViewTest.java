package view.transaction.periodic.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountPeriodicTransactionViewTest {

    private UserAccountPeriodicTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountPeriodicTransactionController controller;
    private UserAccountPeriodicTransactionView view;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountPeriodicTransactionViewModel();
        viewManager = new ViewManagerModel();
        controller = new UserAccountPeriodicTransactionController(null, viewModel); // Assuming null for simplicity
        view = new UserAccountPeriodicTransactionView(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountPeriodicTransactionViewInitialization() {
        assertNotNull(view);
        assertEquals("Periodic Transaction", view.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    @Test
    void testUserAccountPeriodicTransactionPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) view.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(UserAccountPeriodicTransactionPanel.class, contentPane.getClass());
    }
}

