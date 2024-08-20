package interface_adaptors.financial_report.user_account;

import interface_adaptors.financial_report.user_account.UserAccountFinancialReportState;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountFinancialReportViewModelTest {

    private UserAccountFinancialReportViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountFinancialReportViewModel();
    }

    @Test
    void testGetViewName() {
        assertEquals("Financial Report", viewModel.getViewName());
    }

    @Test
    void testGetAndSetState() {
        UserAccountFinancialReportState state = new UserAccountFinancialReportState();
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    void testGetAndSetReportContent() {
        String content = "This is a financial report content.";
        viewModel.setReportContent(content);
        assertEquals(content, viewModel.getReportContent());
    }

    @Test
    void testFirePropertyChanged() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChange();

        assertTrue(listener.isPropertyChanged());
    }

    @Test
    void testResetState() {
        UserAccountFinancialReportState state = new UserAccountFinancialReportState();
        state.setReportContent("Sample Report");
        viewModel.setState(state);

        viewModel.resetState();

        assertNotEquals("Sample Report", viewModel.getState().getReportContent());
        assertEquals("", viewModel.getState().getReportContent());
    }

    // Inner class to test property change listener functionality
    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private boolean propertyChanged = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyChanged = true;
        }

        public boolean isPropertyChanged() {
            return propertyChanged;
        }
    }
}

