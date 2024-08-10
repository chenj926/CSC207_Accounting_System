package view.signup;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.UserAccountSignupController;
import interface_adaptors.signup.UserAccountSignupState;
import interface_adaptors.signup.UserAccountSignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The SignupView class represents the GUI for user signup. It extends JFrame and manages the signup panel.
 */
public class SignupView extends JFrame implements PropertyChangeListener {
    protected final UserAccountSignupViewModel viewModel;
    protected final UserAccountSignupController userAccountSignupController;
    protected final ViewManagerModel viewManager;
    private final SignupPanel signupPanel;

    /**
     * Constructs a SignupView object with the specified view model, controller, and view manager.
     *
     * @param viewModel        the view model for the signup view
     * @param userAccountSignupController the controller for handling signup actions
     * @param viewManager      the view manager for managing view transitions
     */
    public SignupView(UserAccountSignupViewModel viewModel, UserAccountSignupController userAccountSignupController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.userAccountSignupController = userAccountSignupController;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);

        this.signupPanel = new SignupPanel(viewModel, userAccountSignupController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    /**
     * Sets up the UI components for the signup view.
     */
    protected void setupUI() {
        this.getContentPane().add(signupPanel, BorderLayout.CENTER);
    }

    /**
     * Property change event handling for signup results.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountSignupState state = (UserAccountSignupState) evt.getNewValue();

        if (!state.isValid()) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            String successMsg = state.getSuccessMsg();

//            if (successMsg.contains("Shared account already exists")) {
//                // Show choice dialog for shared account existing case
//                int choice = JOptionPane.showOptionDialog(
//                        this,
//                        "Shared account already exists. Would you like to add to it or create a new shared account?",
//                        "Choose Action",
//                        JOptionPane.YES_NO_OPTION,
//                        JOptionPane.QUESTION_MESSAGE,
//                        null,
//                        new String[]{"Add to Existing", "Create New"},
//                        "Add to Existing"
//                );
//
//                if (choice == JOptionPane.YES_OPTION) {
//                    JOptionPane.showMessageDialog(this, "Added to shared account successfully.");
//                    // Handle adding logic here
//                } else {
//                    JOptionPane.showMessageDialog(this, "Create a new shared account.");
//                    // Handle creation logic here
//                }
//            } else {
//                // Normal success message
            JOptionPane.showMessageDialog(this, successMsg);
//                viewManager.setActiveViewName("home page");
//            }
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

