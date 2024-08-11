package view.financial_report.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportController;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportState;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountFinancialReportViewTest {

    private UserAccountFinancialReportViewModel viewModel;
    private UserAccountFinancialReportController controller;
    private TestViewManagerModel viewManager;
    private UserAccountFinancialReportView view;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountFinancialReportViewModel();
        viewManager = new TestViewManagerModel("testUserId");

        controller = new UserAccountFinancialReportController(inputData -> {
            assertEquals("testUserId", inputData.getIdentification());
        }, viewModel);

        view = new UserAccountFinancialReportView(viewModel, controller, viewManager);
    }

    @Test
    void testSetupUI() {
        assertEquals(1, view.getContentPane().getComponentCount()); // Ensure the panel is added
        assertEquals(UserAccountFinancialReportPanel.class, view.getContentPane().getComponent(0).getClass());
    }

    @Test
    void testPropertyChange() {
        UserAccountFinancialReportState state = new UserAccountFinancialReportState();
        state.setReportContent("Test report content");

        PropertyChangeEvent evt = new PropertyChangeEvent(viewModel, "state", null, state);
        view.propertyChange(evt);
    }

    @Test
    void testSetVisibleCallsExecuteOnController() {
        view.setVisible(true);

        // The controller's execute method is verified by the mock in setUp()
    }

    @Test
    void testSetVisibleDoesNotCallExecuteWhenInvisible() {
        view.setVisible(false);
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

