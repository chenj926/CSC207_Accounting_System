package view.transaction.periodic;

import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionState;
import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The PeriodicTransactionView class represents the user interface for managing periodic transactions.
 * It extends JFrame and listens for changes in the associated view model to update the UI accordingly.
 *
 * @author Jessica
 * @author Eric
 */
public class PeriodicTransactionView extends JFrame implements PropertyChangeListener {
    private PeriodicTransactionPanel periodicTransactionPanel;
    private UserAccountPeriodicTransactionViewModel viewModel;

    /**
     * Constructs a PeriodicTransactionView object with the specified view model, controller, and view manager.
     *
     * @param viewModel                  the view model for the periodic transaction view
     * @param userAccountPeriodicTransactionController the controller handling periodic transaction actions
     * @param viewManager                the view manager for handling view transitions
     */
    public PeriodicTransactionView(UserAccountPeriodicTransactionViewModel viewModel,
                                   UserAccountPeriodicTransactionController userAccountPeriodicTransactionController,
                                   ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        periodicTransactionPanel = new PeriodicTransactionPanel(viewModel, userAccountPeriodicTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface layout for the periodic transaction view.
     */
    private void setupUI() {
        this.getContentPane().add(periodicTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Responds to property changes in the view model by updating the UI.
     * Displays a message dialog with the success or error message from the state.
     *
     * @param evt the property change event containing the new state
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountPeriodicTransactionState state = (UserAccountPeriodicTransactionState) evt.getNewValue();
        if (state.getErrorMsg() == null) {
            String successMsg = state.getSuccessMessage();
            JOptionPane.showMessageDialog(this, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String errMsg = state.getErrorMsg();
            JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Makes the view visible and clears the fields in the periodic transaction panel when the view becomes visible.
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            periodicTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
