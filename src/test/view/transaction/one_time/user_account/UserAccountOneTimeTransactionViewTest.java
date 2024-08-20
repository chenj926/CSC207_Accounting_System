package view.transaction.one_time.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserAccountOneTimeTransactionViewTest {

    private UserOneTimeTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private UserAccountOneTimeTransactionController controller;
    private UserAccountOneTimeTransactionView view;

    @BeforeEach
    void setUp() {
        viewModel = new UserOneTimeTransactionViewModel();
        viewManager = new ViewManagerModel();
        controller = new UserAccountOneTimeTransactionController(null, viewModel); // Assuming null for simplicity
        view = new UserAccountOneTimeTransactionView(viewModel, controller, viewManager);
    }

    @Test
    void testUserAccountOneTimeTransactionViewInitialization() {
        assertNotNull(view);
        assertEquals("One Time Transaction", view.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    @Test
    void testUserAccountOneTimeTransactionPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) view.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(UserAccountOneTimeTransactionPanel.class, contentPane.getClass());
    }
}

