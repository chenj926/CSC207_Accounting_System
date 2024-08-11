package view.financial_report.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportController;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountFinancialReportPanelTest {

    private UserAccountFinancialReportViewModel viewModel;
    private UserAccountFinancialReportController controller;
    private TestViewManagerModel viewManager; // Custom TestViewManagerModel
    private UserAccountFinancialReportPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountFinancialReportViewModel();
        viewManager = new TestViewManagerModel("testUserId");

        controller = new UserAccountFinancialReportController(inputData -> {
            assertEquals("testUserId", inputData.getIdentification());
        }, viewModel);

        panel = new UserAccountFinancialReportPanel(viewModel, controller, viewManager);
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

        assertEquals("Homepage Two", viewManager.getActiveViewName());
    }

    @Test
    void testRefreshData() {
        panel.refreshData();
    }

    @Test
    void testClearFields() {
        panel.clearFields();
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
    }
}

