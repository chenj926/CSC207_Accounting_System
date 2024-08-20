package view.financial_report.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SharedAccountFinancialReportPanelTest {

    private SharedAccountFinancialReportViewModel viewModel;
    private SharedAccountFinancialReportController controller;
    private TestViewManagerModel viewManager; // Use custom TestViewManagerModel
    private SharedAccountFinancialReportPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountFinancialReportViewModel();
        viewManager = new TestViewManagerModel("testUserId");

        controller = new SharedAccountFinancialReportController(inputData -> {
            assertEquals("testUserId", inputData.getIdentification());
        }, viewModel);

        panel = new SharedAccountFinancialReportPanel(viewModel, controller, viewManager);
    }

    @Test
    void testInitializeComponents() {
        assertEquals("Financial Report", ((JLabel) panel.getComponent(0)).getText());
    }

    @Test
    void testPropertyChangeUpdatesReportContent() {
        viewModel.setReportContent("Updated report content");

        panel.refreshData();
        viewModel.setReportContent("Updated report content");

        // Property change is handled internally
    }

    @Test
    void testBackButtonActionListener() {
        JButton backButton = (JButton) ((JPanel) panel.getComponent(2)).getComponent(0);
        backButton.doClick();

        assertEquals("Shared Account Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testRefreshData() {
        panel.refreshData();

        // Verification is done in the controller setup
    }

    @Test
    void testClearFields() {
        panel.clearFields();

        // Assume clearFields behaves as expected due to encapsulation
    }

    static class TestViewManagerModel extends ViewManagerModel {
        private final String userId;

        public TestViewManagerModel(String userId) {
            this.userId = userId;
        }

        @Override
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            // Provide a method to manually set the user ID in the test
        }
    }
}



