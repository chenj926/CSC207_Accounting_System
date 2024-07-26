package view.Signup;

import interface_adaptors.*;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupState;
import interface_adaptors.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * The SignupView class represents the GUI for user signup. It extends JFrame and implements PropertyChangeListener
 * to update the view based on changes in the view model.
 *
 * @author Jessica
 * @author Eric
 */
public class SignupView extends JFrame implements PropertyChangeListener {
    private final SignupViewModel viewModel;
    private final SignupPanel signupPanel;

    /**
     * Constructs a SignupView object with the specified view model, controller, and view manager.
     *
     * @param viewModel        the view model for the signup view
     * @param signupController the controller for handling signup actions
     * @param viewManager      the view manager for managing view transitions
     */
    public SignupView(SignupViewModel viewModel, SignupController signupController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        signupPanel = new SignupPanel(viewModel, signupController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the UI components for the signup view.
     */
    private void setupUI() {
        this.getContentPane().add(signupPanel, BorderLayout.CENTER);
    }

    /**
     * Called when a bound property is changed. Displays a message dialog with the error or success message.
     *
     * @param evt the event that characterizes the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (!state.isValid()) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        }
        else {
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
        }
    }

    /**
     * Overrides the setVisible method to clear fields when the view becomes visible.
     *
     * @param visible true to make the view visible, false to make it invisible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            signupPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }

}
