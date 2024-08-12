package view.login.user_account;

import interface_adaptors.*;
import interface_adaptors.login.user_account.UserAccountLoginController;
import interface_adaptors.login.user_account.UserAccountLoginState;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The HomePagePanel class represents the panel for the home page in the application.
 * It extends JPanel and is responsible for displaying the home page interface, including
 * buttons for login, signup, and exit, along with a background image.
 *
 * @author Jessica
 * @author Eric
 */
public class UserAccountLoginView extends JFrame implements PropertyChangeListener {
    private UserAccountLoginViewModel viewModel;
    private UserAccountLoginPanel userAccountLoginPanel;

    /**
     * Constructs a UserAccountLoginView object with the specified view model, login controller, and view manager.
     *
     * @param viewModel       the view model for the login view
     * @param loginController the controller handling login actions
     * @param viewManager     the view manager for managing view transitions
     */
    public UserAccountLoginView(UserAccountLoginViewModel viewModel, UserAccountLoginController loginController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        userAccountLoginPanel = new UserAccountLoginPanel(viewModel, loginController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the user interface for the login view.
     * Adds the login panel to the content pane of the frame.
     */
    private void setupUI() {
        this.getContentPane().add(userAccountLoginPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events to update the login view based on state changes.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountLoginState state = (UserAccountLoginState) evt.getNewValue();
        if (state.getSuccessMsg() == null) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
        }
    }

    /**
     * Sets the visibility of the login view.
     * Clears the fields in the login panel when the view becomes visible.
     *
     * @param visible true to make the view visible, false to hide it
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            userAccountLoginPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
