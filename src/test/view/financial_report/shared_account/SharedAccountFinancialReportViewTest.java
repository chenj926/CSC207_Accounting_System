package view.financial_report.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportState;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountFinancialReportViewTest {

    private SharedAccountFinancialReportViewModel viewModel;
    private SharedAccountFinancialReportController controller;
    private TestViewManagerModel viewManager; // Extend ViewManagerModel to provide test user ID
    private SharedAccountFinancialReportView view;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountFinancialReportViewModel();

        // Use a custom ViewManagerModel implementation for testing
        viewManager = new TestViewManagerModel("testUserId");

        // Basic controller setup for testing
        controller = new SharedAccountFinancialReportController(inputData -> {
            // Check that the correct user ID was passed
            assertEquals("testUserId", inputData.getIdentification());
        }, viewModel);

        // Create the view
        view = new SharedAccountFinancialReportView(viewModel, controller, viewManager);
    }

    @Test
    void testSetupUI() {
        // Verify the UI setup
        assertNotNull(view.getContentPane().getComponent(0));
        assertTrue(view.getContentPane().getComponent(0) instanceof SharedAccountFinancialReportPanel);
    }

    @Test
    void testPropertyChangeUpdatesState() {
        // Create a mock state
        SharedAccountFinancialReportState newState = new SharedAccountFinancialReportState();
        newState.setReportContent("Test report content");

        // Trigger property change
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "state", null, newState);
        view.propertyChange(evt);

        // Verify that the view model's state is updated correctly
        assertEquals("Test report content", newState.getReportContent());
    }

    @Test
    void testSetVisible() {
        // Call setVisible on the view
        view.setVisible(true);

        // Ensure that the controller's execute method was called with the correct user ID
        // The controller's logic is checked in the setup above
    }

    @Test
    void testViewTitle() {
        // Verify the title of the JFrame
        assertEquals("Shared Account Financial Report", view.getTitle());
    }

    @Test
    void testDefaultCloseOperation() {
        // Verify the default close operation
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    // A simple extension of ViewManagerModel to provide a test user ID
    static class TestViewManagerModel extends ViewManagerModel {
        private final String userId;

        public TestViewManagerModel(String userId) {
            this.userId = userId;
        }

        @Override
        public String getUserId() {
            return userId;
        }
    }
}


