package view.home_page.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoController;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoState;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.homepage.user_account.UserAccountHomepageTwoInputBoundary;
import use_case.homepage.user_account.UserAccountHomepageTwoInputData;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountHomepageTwoPanelTest {

    private UserAccountHomepageTwoViewModel viewModel;
    private TestViewManagerModel viewManager;
    private UserAccountHomepageTwoController controller;
    private UserAccountHomepageTwoPanel panel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountHomepageTwoViewModel();
        viewManager = new TestViewManagerModel();
        UserAccountHomepageTwoInputBoundary inputBoundary = new TestInputBoundary();
        controller = new UserAccountHomepageTwoController(inputBoundary, viewModel);
        panel = new UserAccountHomepageTwoPanel(viewModel, viewManager, controller);
    }

    @Test
    void testInitializeComponents() {
        // Checking if title label was initialized correctly
        JLabel titleLabel = (JLabel) getComponentAtIndex(panel, 0);
        assertEquals(viewModel.getTITLE_LABEL(), titleLabel.getText());
    }

    @Test
    void testOneTimeButtonListener() {
        JButton oneTimeButton = getButtonByText(panel, viewModel.getONE_TIME_BUTTON_LABEL());
        oneTimeButton.doClick();
        assertEquals("One Time Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testPeriodicButtonListener() {
        JButton periodicButton = getButtonByText(panel, viewModel.getPERIODIC_BUTTON_LABEL());
        periodicButton.doClick();
        assertEquals("Periodic Transaction", viewManager.getActiveViewName());
    }

    @Test
    void testFinancialReportButtonListener() {
        JButton financialReportButton = getButtonByText(panel, viewModel.getFINANCIAL_REPORT_BUTTON_LABEL());
        financialReportButton.doClick();
        assertEquals("Financial Report", viewManager.getActiveViewName());
    }

    @Test
    void testLogoutButtonListener() {
        JButton logoutButton = getButtonByText(panel, viewModel.getCANCEL_BUTTON_LABEL());
        logoutButton.doClick();
        assertEquals("home page", viewManager.getActiveViewName());
    }

    @Test
    void testUIUpdate() {
        UserAccountHomepageTwoState state = new UserAccountHomepageTwoState();
        state.setBasicUserInfo(new String[]{"TestUser", "1000", "200", "800", "SharedAccount1"});
        viewModel.setState(state);

        panel.updateUI();

        assertEquals("User:", getLabelValue(panel, viewModel.getUSERNAME_LABEL()));
        assertEquals("Total Income:", getLabelValue(panel, viewModel.getINCOME_LABEL()));
        assertEquals("Total Outflow:", getLabelValue(panel, viewModel.getOUTFLOW_LABEL()));
        assertEquals("Total Balance:", getLabelValue(panel, viewModel.getBALANCE_LABEL()));
        assertEquals("Related Shared Account Ids: ", getLabelValue(panel, viewModel.getSHAREDIDS_LABEL()));
    }


    private Component getComponentAtIndex(UserAccountHomepageTwoPanel panel, int index) {
        return panel.getComponent(index);
    }

    private JButton getButtonByText(UserAccountHomepageTwoPanel panel, String text) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(text)) {
                    return button;
                }
            } else if (component instanceof JPanel) {
                JPanel panelComponent = (JPanel) component;
                for (Component innerComponent : panelComponent.getComponents()) {
                    if (innerComponent instanceof JButton) {
                        JButton button = (JButton) innerComponent;
                        if (button.getText().equals(text)) {
                            return button;
                        }
                    }
                }
            }
        }
        return null;
    }

    private String getLabelValue(UserAccountHomepageTwoPanel panel, String labelName) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().equals(labelName)) {
                    return label.getText();
                }
            }
        }
        return null;
    }

    static class TestViewManagerModel extends ViewManagerModel {
        private String activeViewName;

        @Override
        public String getActiveViewName() {
            return activeViewName;
        }

        @Override
        public void setActiveViewName(String viewName) {
            this.activeViewName = viewName;
        }
    }

    static class TestInputBoundary implements UserAccountHomepageTwoInputBoundary {

        @Override
        public void execute(UserAccountHomepageTwoInputData inputData) {
            // Mock implementation for testing
            assertEquals("testUserId", inputData.getId());
        }
    }
}


