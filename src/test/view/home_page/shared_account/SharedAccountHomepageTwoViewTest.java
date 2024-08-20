package view.home_page.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoController;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoState;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SharedAccountHomepageTwoViewTest {

    private SharedAccountHomepageTwoViewModel viewModel;
    private SharedAccountHomepageTwoController controller;
    private TestViewManagerModel viewManager;
    private SharedAccountHomepageTwoView view;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountHomepageTwoViewModel();
        viewManager = new TestViewManagerModel("testUserId");

        controller = new SharedAccountHomepageTwoController(inputData -> {
            assertEquals("testUserId", inputData.getId());
        }, viewModel);

        view = new SharedAccountHomepageTwoView(viewModel, viewManager, controller);
    }

    @Test
    void testUIInitialization() {
        assertEquals("Shared Account Homepage Two", view.getTitle());
        assertEquals(600, view.getWidth());
        assertEquals(600, view.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());
    }

    @Test
    void testPropertyChange() {
        SharedAccountHomepageTwoState state = new SharedAccountHomepageTwoState();
        String[] userInfo = {"Test User", "1000.00", "500.00", "500.00"};
        state.setBasicUserInfo(userInfo);

        PropertyChangeEvent evt = new PropertyChangeEvent(this, "state", null, state);
        view.propertyChange(evt);

        // Here we assume the view would properly update if required
        // but there is no direct UI update in the current view implementation.
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


