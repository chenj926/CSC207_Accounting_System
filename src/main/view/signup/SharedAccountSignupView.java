package view.signup;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SharedAccountSignupViewModel; // Use specific ViewModel for shared accounts
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SharedAccountSignupController;

import javax.swing.*;
import java.awt.*;

/**
 * The SharedAccountSignupView class represents the GUI for shared account signup.
 * It extends SignupView and manages the shared account signup panel.
 */
public class SharedAccountSignupView extends SignupView {

    private final SharedAccountSignupViewModel sharedViewModel; // Specific view model for shared accounts
    private final SharedAccountSignupPanel sharedAccountSignupPanel; // Panel for shared account signup

    /**
     * Constructs a SharedAccountSignupView object with the specified view model, controller, and view manager.
     *
     * @param viewModel        the view model for the shared account signup view
     * @param sharedSignupController the controller for handling signup actions
     * @param viewManager      the view manager for managing view transitions
     */
    public SharedAccountSignupView(SharedAccountSignupViewModel viewModel, SharedAccountSignupController sharedSignupController, ViewManagerModel viewManager) {
        super(viewModel, sharedSignupController, viewManager);  // Ensure correct super constructor call
        this.sharedViewModel = viewModel;
        this.sharedAccountSignupPanel = new SharedAccountSignupPanel(sharedViewModel, sharedSignupController, viewManager);  // Ensure initialization
        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    @Override
    protected void setupUI() {
        this.getContentPane().removeAll(); // Clear previous components

        if (sharedAccountSignupPanel != null) { // Check for null
            this.getContentPane().add(sharedAccountSignupPanel, BorderLayout.CENTER); // Add panel
        }

        this.revalidate(); // Refresh layout
        this.repaint(); // Repaint frame
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
            sharedAccountSignupPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
