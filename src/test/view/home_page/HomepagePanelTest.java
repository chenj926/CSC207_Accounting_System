package view.home_page;

import interface_adaptors.HomePageViewModel;
import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class HomepagePanelTest {

    private HomePagePanel panel;
    private HomePageViewModel viewModel;
    private TestViewManagerModel viewManager;

    @BeforeEach
    void setUp() {
        viewModel = new HomePageViewModel();
        viewManager = new TestViewManagerModel();
        panel = new HomePagePanel(viewModel, viewManager);
    }

    @Test
    void testInitializeComponents() {
        assertEquals("Welcome to Accounting System", ((JLabel) panel.getComponent(0)).getText());
    }

    @Test
    void testLoginButtonListener() {
        JButton loginButton = getButtonAtIndex(panel, 0);
        loginButton.doClick();
        assertEquals("log in", viewManager.getActiveViewName());
    }

    @Test
    void testSharedAccountLoginButtonListener() {
        JButton sharedAccountLoginButton = getButtonAtIndex(panel, 2);
        sharedAccountLoginButton.doClick();
        assertEquals("shared account log in", viewManager.getActiveViewName());
    }

    @Test
    void testSignupButtonListener() {
        JButton signupButton = getButtonAtIndex(panel, 1);
        signupButton.doClick();
        assertEquals("sign up", viewManager.getActiveViewName());
    }

    @Test
    void testSharedAccountSignupButtonListener() {
        JButton sharedAccountSignupButton = getButtonAtIndex(panel, 3);
        sharedAccountSignupButton.doClick();
        assertEquals("shared account sign up", viewManager.getActiveViewName());
    }


    private JButton getButtonAtIndex(HomePagePanel panel, int index) {
        JPanel centerPanel = (JPanel) panel.getComponent(1);
        return (JButton) centerPanel.getComponent(index);
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
}



