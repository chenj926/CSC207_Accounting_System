package interface_adaptors.financial_report.user_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountFinancialReportViewModelTest {

    private UserAccountFinancialReportViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountFinancialReportViewModel();
    }

    @Test
    void testInitialState() {
        // Test that the initial state is set correctly
        UserAccountFinancialReportState state = viewModel.getState();
        assertNotNull(state);
        assertEquals("", viewModel.getReportContent());
    }

    @Test
    void testGetState() {
        // Test that the state can be retrieved correctly
        UserAccountFinancialReportState state = viewModel.getState();
        assertNotNull(state);
        assertEquals("", state.getReportContent());
    }

    @Test
    void testSetState() {
        // Test that the state can be set and retrieved correctly
        UserAccountFinancialReportState newState = new UserAccountFinancialReportState();
        newState.setReportContent("New financial report content");

        viewModel.setState(newState);

        assertEquals(newState, viewModel.getState());
        assertEquals("New financial report content", viewModel.getState().getReportContent());
    }

    @Test
    void testResetState() {
        // Test resetting the state to a new instance
        UserAccountFinancialReportState newState = new UserAccountFinancialReportState();
        newState.setReportContent("Initial report content");

        viewModel.setState(newState);
        assertEquals("Initial report content", viewModel.getState().getReportContent());

        viewModel.resetState();

        UserAccountFinancialReportState resetState = viewModel.getState();
        assertNotNull(resetState);
        assertEquals("", resetState.getReportContent()); // Verify that report content is reset
    }

    @Test
    void testGetViewName() {
        // Test that the view name is returned correctly
        assertEquals("Financial Report", viewModel.getViewName());
    }

    @Test
    void testSetReportContent() {
        // Test setting the report content
        String content = "This is the financial report content.";
        viewModel.setReportContent(content);
        assertEquals(content, viewModel.getReportContent());
    }
}

