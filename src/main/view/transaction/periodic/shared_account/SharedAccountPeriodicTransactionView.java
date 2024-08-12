package view.transaction.periodic.shared_account;

import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The SharedAccountPeriodicTransactionView class represents the user interface for managing shared account periodic transactions.
 * It extends JFrame and listens for changes in the associated view model to update the UI accordingly.
 *
 */
public class SharedAccountPeriodicTransactionView extends JFrame implements PropertyChangeListener {
    private SharedAccountPeriodicTransactionPanel sharedAccountPeriodicTransactionPanel;
    private SharedAccountPeriodicTransactionViewModel viewModel;

    /**
     * Constructs a SharedAccountPeriodicTransactionView object with the specified view model, controller, and view manager.
     *
     * @param viewModel the view model for the shared account periodic transaction view
     * @param sharedAccountPeriodicTransactionController the controller handling shared account periodic transaction actions
     * @param viewManager the view manager for handling view transitions
     */
    public SharedAccountPeriodicTransactionView(SharedAccountPeriodicTransactionViewModel viewModel,
                                                SharedAccountPeriodicTransactionController sharedAccountPeriodicTransactionController,
                                                ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        sharedAccountPeriodicTransactionPanel = new SharedAccountPeriodicTransactionPanel(viewModel, sharedAccountPeriodicTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);  // Adjusted size to accommodate additional UI components
        setVisible(true);
    }

    /**
     * Sets up the user interface layout for the shared account periodic transaction view.
     */
    private void setupUI() {
        this.getContentPane().add(sharedAccountPeriodicTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Responds to property changes in the view model by updating the UI.
     * Displays a message dialog with the success or error message from the state.
     *
     * @param evt the property change event containing the new state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountPeriodicTransactionState state = (SharedAccountPeriodicTransactionState) evt.getNewValue();
        if (state.getErrorMsg() == null) {
            String successMsg = state.getSuccessMessage();
            System.out.println(successMsg);
            JOptionPane.showMessageDialog(this, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String errMsg = state.getErrorMsg();
            JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Makes the view visible and clears the fields in the shared account periodic transaction panel when the view becomes visible.
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            sharedAccountPeriodicTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}

