package view.home_page;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoViewModel;
import view.transaction.TransactionPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomepageTwoView extends JFrame implements PropertyChangeListener {

    private final HomepageTwoViewModel viewModel;
    private final HomepageTwoPanel homepageTwoPanel;

    /**
     * Constructs a TransactionView with the specified view model and view manager.
     *
     * @param viewModel the view model for the transaction view
     * @param viewManager           the view manager for handling view transitions
     */
    public HomepageTwoView(HomepageTwoViewModel viewModel, ViewManagerModel viewManager) {
        super("Transaction View");
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.homepageTwoPanel = new HomepageTwoPanel(viewModel, viewManager);

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
    }
}
