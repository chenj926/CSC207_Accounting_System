package view.transaction.one_time;

import interface_adaptors.*;
import interface_adaptors.transaction.one_time.OneTimeTransactionController;
import interface_adaptors.transaction.one_time.OneTimeTransactionState;
import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The OneTimeTransactionView class represents the user interface for a one-time transaction feature.
 * It extends JFrame and implements PropertyChangeListener to handle property changes in the view model.
 *
 * @author Jessica
 * @author Eric
 */
public class OneTimeTransactionView extends JFrame implements PropertyChangeListener {
    private OneTimeTransactionPanel oneTimeTransactionPanel;
    private OneTimeTransactionViewModel viewModel;

    /**
     * Constructs a OneTimeTransactionView object with the specified view model, controller, and view manager.
     *
     * @param viewModel                the view model for the one-time transaction view
     * @param oneTimeTransactionController the controller handling one-time transaction actions
     * @param viewManager              the view manager for handling view transitions
     */
    public OneTimeTransactionView(OneTimeTransactionViewModel viewModel,
                                  OneTimeTransactionController oneTimeTransactionController,
                                  ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        oneTimeTransactionPanel = new OneTimeTransactionPanel(viewModel, oneTimeTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the one-time transaction panel to the frame.
     */
    private void setupUI() {
        this.getContentPane().add(oneTimeTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property changes in the view model.
     * Displays error or success messages based on the state of the transaction.
     *
     * @param evt the property change event containing the new value of the state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        OneTimeTransactionState state = (OneTimeTransactionState) evt.getNewValue();
        if (state.getSuccessMessage() == null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMessage());
        }
    }

    /**
     * Makes the view visible and clears the transaction fields when the view becomes visible.
     *
     * @param visible boolean indicating whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            oneTimeTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }

}
