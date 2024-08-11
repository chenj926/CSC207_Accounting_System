package view.home_page.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoController;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoState;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountHomepageTwoViewTest {

    private UserAccountHomepageTwoViewModel viewModel;
    private UserAccountHomepageTwoController controller;
    private TestViewManagerModel viewManager;
    private UserAccountHomepageTwoView view;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountHomepageTwoViewModel();
        viewManager = new TestViewManagerModel("testUserId");

        controller = new UserAccountHomepageTwoController(inputData -> {
            assertEquals("testUserId", inputData.getId());
        }, viewModel);

        view = new UserAccountHomepageTwoView(viewModel, viewManager, controller);
    }

    @Test
    void testUIInitialization() {
        assertEquals("Homepage Two", view.getTitle());
        assertEquals(600, view.getWidth());
        assertEquals(600, view.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    @Test
    void testPropertyChange() {
        UserAccountHomepageTwoState state = new UserAccountHomepageTwoState();
        String[] userInfo = {"Test User", "1000.00", "500.00", "500.00", "Related Accounts"};
        state.setBasicUserInfo(userInfo);

        PropertyChangeEvent evt = new PropertyChangeEvent(this, "state", null, state);
        view.propertyChange(evt);
    }

    @Test
    void testSetVisible() {
        view.setVisible(true);
        assertEquals("testUserId", viewManager.getUserId());
    }

    static class TestViewManagerModel extends ViewManagerModel {
        private String activeViewName;
        private final String userId;

        public TestViewManagerModel(String userId) {
            this.userId = userId;
        }

        @Override
        public String getUserId() {
            return userId;
        }

        @Override
        public void setActiveViewName(String viewName) {
            this.activeViewName = viewName;
        }

        @Override
        public String getActiveViewName() {
            return this.activeViewName;
        }
    }
}

