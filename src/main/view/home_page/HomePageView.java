package view.home_page;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The HomePageView class represents the GUI for the home page. It extends JFrame and implements PropertyChangeListener
 * to update the view based on changes in the view model.
 *
 * @author Dana
 * @author Eric
 */
public class HomePageView extends JFrame implements PropertyChangeListener {
    private HomePageViewModel viewModel;
    private HomePagePanel homePagePanel;

    /**
     * Constructs a HomePageView object with the specified view model and view manager.
     *
     * @param viewModel   the view model for the home page view
     * @param viewManager the view manager for managing view transitions
     */
    public HomePageView(HomePageViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.homePagePanel = new HomePagePanel(viewModel, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);

    }

    /**
     * Sets up the UI components for the home page view.
     */
    private void setupUI() {
        this.getContentPane().add(homePagePanel, BorderLayout.CENTER);
    }

    /**
     * Called when a bound property is changed. Currently, this method does not perform any actions.
     *
     * @param evt the event that characterizes the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // do something with the propertyChange
    }
}


