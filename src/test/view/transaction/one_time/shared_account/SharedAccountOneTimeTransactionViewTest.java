package view.transaction.one_time.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountOneTimeTransactionViewTest {

    private SharedAccountOneTimeTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountOneTimeTransactionController controller;
    private SharedAccountOneTimeTransactionView view;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountOneTimeTransactionViewModel();
        viewManager = new ViewManagerModel();
        controller = new SharedAccountOneTimeTransactionController(null, viewModel); // Assuming null for simplicity
        view = new SharedAccountOneTimeTransactionView(viewModel, controller, viewManager);
    }

    @Test
    void testSharedAccountOneTimeTransactionViewInitialization() {
        assertNotNull(view);
        assertEquals("Shared Account One Time Transaction", view.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    @Test
    void testSharedAccountOneTimeTransactionPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) view.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(SharedAccountOneTimeTransactionPanel.class, contentPane.getClass());
    }
}

