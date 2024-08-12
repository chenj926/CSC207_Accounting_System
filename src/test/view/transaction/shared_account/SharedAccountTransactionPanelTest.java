package view.transaction.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.SharedAccountTransactionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountTransactionPanelTest {

    private SharedAccountTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountTransactionPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountTransactionViewModel();
        viewManager = new ViewManagerModel();
        panel = new SharedAccountTransactionPanel(viewModel, viewManager);
    }

    @Test
    void testSharedAccountTransactionPanelInitialization() {
        assertNotNull(panel);
        assertEquals("Shared Account Transaction", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

    @Test
    void testOneTimeButtonActionListener() {
        JButton oneTimeButton = (JButton) panel.getComponent(7);
        oneTimeButton.doClick();
        assertEquals("Shared Account One Time Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testPeriodicButtonActionListener() {
        JButton periodicButton = (JButton) panel.getComponent(8);
        periodicButton.doClick();
        assertEquals("Shared Account Periodic Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testAddUserButtonActionListener() {
        JButton addUserButton = (JButton) panel.getComponent(9);
        addUserButton.doClick();
        // Typically, we'd check for some change in state or call, but as it's a JOptionPane, we can only verify it didn't crash
    }

    @Test
    void testFinancialReportButtonActionListener() {
        JButton financialReportButton = (JButton) panel.getComponent(10);
        financialReportButton.doClick();
        assertEquals("Financial Report", viewManager.getActiveViewName());
    }

    @Test
    void testLogoutButtonActionListener() {
        JButton logoutButton = (JButton) panel.getComponent(11);
        logoutButton.doClick();
        assertEquals("home page", viewManager.getActiveViewName());
        assertEquals(null, viewManager.getUserId());
    }
}

