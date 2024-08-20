package view.transaction.one_time.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SharedAccountOneTimeTransactionPanelTest {

    private SharedAccountOneTimeTransactionViewModel viewModel;
    private ViewManagerModel viewManager;
    private SharedAccountOneTimeTransactionController controller;
    private SharedAccountOneTimeTransactionPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountOneTimeTransactionViewModel();
        viewManager = new ViewManagerModel();

        // Create a dummy implementation of SharedAccountOneTimeTransactionInputBoundary
        SharedAccountOneTimeTransactionInputBoundary dummyInteractor = new SharedAccountOneTimeTransactionInputBoundary() {
            @Override
            public void execute(SharedAccountOneTimeTransactionInputData inputData) {
                // Dummy implementation: Do nothing
            }
        };

        controller = new SharedAccountOneTimeTransactionController(dummyInteractor, viewModel);
        panel = new SharedAccountOneTimeTransactionPanel(viewModel, controller, viewManager);
    }


    @Test
    void testSharedAccountOneTimeTransactionPanelInitialization() {
        assertNotNull(panel);
        assertEquals("One Time Transaction", ((JLabel) panel.getComponent(0)).getText()); // Check title
    }

    @Test
    void testSubmitButtonActionListener() {
        JTextField userIdField = (JTextField) panel.getComponent(2);
        JTextField amountField = (JTextField) panel.getComponent(4);
        JTextField dateField = (JTextField) panel.getComponent(6);
        JTextField descriptionField = (JTextField) panel.getComponent(8);
        JComboBox<String> categoryComb = (JComboBox<String>) panel.getComponent(10);

        // Set values for testing
        userIdField.setText("user123");
        amountField.setText("100.00");
        dateField.setText("2024-08-12");
        descriptionField.setText("Lunch with team");
        categoryComb.setSelectedItem("Food");

        // Find the submit button
        JPanel buttonsPanel = (JPanel) panel.getComponent(11);
        JButton submitButton = (JButton) buttonsPanel.getComponent(0);
        submitButton.doClick();

        // Verify the transaction state are cleared after submitting
        assertEquals("0", viewModel.getState().getTransactionAmount());
        assertEquals(null, viewModel.getState().getTransactionDate());
        assertEquals("", viewModel.getState().getTransactionDescription());
        assertEquals("", viewModel.getState().getTransactionCategory());
    }



    @Test
    void testCancelButtonActionListener() {
        JPanel buttonsPanel = (JPanel) panel.getComponent(11);
        JButton cancelButton = (JButton) buttonsPanel.getComponent(1);
        cancelButton.doClick();

        assertEquals("Shared Account Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testClearFields() {
        JTextField userIdField = (JTextField) panel.getComponent(2);
        JTextField amountField = (JTextField) panel.getComponent(4);
        JTextField dateField = (JTextField) panel.getComponent(6);
        JTextField descriptionField = (JTextField) panel.getComponent(8);
        JComboBox<String> categoryComb = (JComboBox<String>) panel.getComponent(10);

        // Set initial values
        userIdField.setText("user123");
        amountField.setText("100.00");
        dateField.setText("2024-08-12");
        descriptionField.setText("Lunch with team");
        categoryComb.setSelectedItem("Food");

        // Clear fields
        panel.clearFields();

        // Verify fields are cleared
        assertEquals("user123", userIdField.getText());
        assertEquals("", amountField.getText());
        assertEquals("", dateField.getText());
        assertEquals("", descriptionField.getText());
        assertEquals(0, categoryComb.getSelectedIndex());  // Assuming the first category is the default
    }

}

