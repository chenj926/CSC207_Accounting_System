package view.home_page;

import interface_adaptors.HomePageViewModel;
import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HomepageViewTest {

    private HomePageViewModel viewModel;
    private ViewManagerModel viewManager;
    private HomePageView homePageView;

    @BeforeEach
    void setUp() {
        viewModel = new HomePageViewModel();
        viewManager = new ViewManagerModel();
        homePageView = new HomePageView(viewModel, viewManager);
    }

    @Test
    void testHomePageViewInitialization() {
        assertNotNull(homePageView);
        assertEquals("Welcome to Accounting System", homePageView.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, homePageView.getDefaultCloseOperation());
    }

    @Test
    void testHomePagePanelIsAddedToContentPane() {
        JPanel contentPane = (JPanel) homePageView.getContentPane().getComponent(0);
        assertNotNull(contentPane);
        assertEquals(HomePagePanel.class, contentPane.getClass());
    }
}

