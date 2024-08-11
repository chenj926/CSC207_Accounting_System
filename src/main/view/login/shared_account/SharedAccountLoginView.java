package view.login.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.shared_account.SharedAccountLoginController;
import interface_adaptors.login.shared_account.SharedAccountLoginState;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The SharedAccountLoginView class represents the view for shared account login.
 * It displays the user interface for logging into a shared account.
 */
public class SharedAccountLoginView extends JFrame implements PropertyChangeListener {
    private SharedAccountLoginViewModel sharedViewModel;
//    private SharedAccountLoginController sharedLoginController;
    private SharedAccountLoginPanel sharedAccountLoginPanel;

    /**
     * Constructs a SharedAccountLoginView object with the specified view model, login controller, and view manager.
     *
     * @param sharedViewModel       the shared account login view model
     * @param sharedAccountLoginController the controller handling login actions
     * @param viewManager     the view manager for managing view transitions
     */
    public SharedAccountLoginView(SharedAccountLoginViewModel sharedViewModel, SharedAccountLoginController sharedAccountLoginController, ViewManagerModel viewManager) {
        super(sharedViewModel.getTitleLabel());
        this.sharedViewModel = sharedViewModel;
//        this.sharedAccountLoginController = sharedAccountLoginController;
        this.sharedViewModel.addPropertyChangeListener(this);

        sharedAccountLoginPanel = new SharedAccountLoginPanel(sharedViewModel, sharedAccountLoginController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface for the shared account login view.
     * Adds the shared account login panel to the content pane of the frame.
     */
    private void setupUI() {
        this.getContentPane().add(sharedAccountLoginPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events to update the shared account login view based on state changes.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountLoginState state = (SharedAccountLoginState) evt.getNewValue();
        if (state.getSuccessMsg() == null) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
        }
    }

    /**
     * Sets the visibility of the shared account login view.
     * Clears the fields in the shared account login panel when the view becomes visible.
     *
     * @param visible true to make the view visible, false to hide it
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            sharedAccountLoginPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}


