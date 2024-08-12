package view.transaction.periodic.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountPeriodicTransactionViewTest {

    private SharedAccountPeriodicTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountPeriodicTransactionController controller;
    private SharedAccountPeriodicTransactionView view;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountPeriodicTransactionViewModel();
        viewManager = new ViewManagerModel();
        controller = new SharedAccountPeriodicTransactionController(null, viewModel); // Assuming null for simplicity
        view = new SharedAccountPeriodicTransactionView(viewModel, controller, viewManager);
    }

    @Test
    void testSharedAccountPeriodicTransactionViewInitialization() {
        assertNotNull(view);
        assertEquals("Shared Account Periodic Transaction", view.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    @Test
    void testSharedAccountPeriodicTransactionPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) view.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(SharedAccountPeriodicTransactionPanel.class, contentPane.getClass());
    }
}

