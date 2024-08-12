package view.transaction.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.SharedAccountTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountTransactionViewTest {

    private SharedAccountTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountTransactionView view;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountTransactionViewModel();
        viewManager = new ViewManagerModel();
        view = new SharedAccountTransactionView(viewModel, viewManager);
    }

    @Test
    void testSharedAccountTransactionViewInitialization() {
        assertNotNull(view);
        assertEquals("Shared Account Transaction View", view.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    @Test
    void testSharedAccountTransactionPanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) view.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(SharedAccountTransactionPanel.class, contentPane.getClass());
    }
}

