package interface_adaptors.financial_report.shared_account;

import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportState;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountFinancialReportViewModelTest {

    private SharedAccountFinancialReportViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountFinancialReportViewModel();
    }

    @Test
    void testGetViewName() {
        assertEquals("Shared Account Financial Report", viewModel.getViewName());
    }

    @Test
    void testGetAndSetState() {
        SharedAccountFinancialReportState state = new SharedAccountFinancialReportState();
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
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
        SharedAccountFinancialReportState state = new SharedAccountFinancialReportState();
        state.setReportContent("Sample Report");
        viewModel.setState(state);

        viewModel.resetState();

        assertNotEquals("Sample Report", viewModel.getState().getReportContent());
        assertEquals("", viewModel.getState().getReportContent());
    }

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

