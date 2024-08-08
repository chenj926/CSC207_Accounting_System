package view.home_page;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoController;
import interface_adaptors.homepage.HomepageTwoState;
import interface_adaptors.homepage.HomepageTwoViewModel;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class HomepageTwoView extends JFrame implements PropertyChangeListener {

    private final HomepageTwoViewModel viewModel;
    private final HomepageTwoPanel homepageTwoPanel;
    private HomepageTwoController controller;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a TransactionView with the specified view model and view manager.
     *
     * @param viewModel the view model for the transaction view
     * @param viewManager           the view manager for handling view transitions
     */
    public HomepageTwoView(HomepageTwoViewModel viewModel, ViewManagerModel viewManager,
                           HomepageTwoController homepageTwoController) {
        super("Homepage Two");
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.viewManager = viewManager;
        this.controller = homepageTwoController;

        // debug
        System.out.println("checking viewModel value"+Arrays.toString(this.viewModel.getBasicUserInfo()));

        this.homepageTwoPanel = new HomepageTwoPanel(this.viewModel, this.viewManager, this.controller);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the transaction panel to the content pane.
     */
    private void setupUI() {
        this.getContentPane().add(homepageTwoPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events. Currently, this method does not perform any actions but can be
     * customized to handle changes in the view model's properties.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here if needed
        HomepageTwoState state = (HomepageTwoState) evt.getNewValue();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            String id = this.viewManager.getUserId();
            this.controller.execute(id);
        }
    }

}
