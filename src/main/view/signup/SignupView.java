package view.signup;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The SignupView class represents the GUI for user signup. It extends JFrame and manages the signup panel.
 */
public class SignupView extends JFrame {
    protected final SignupViewModel viewModel;
    protected final SignupController signupController;
    protected final ViewManagerModel viewManager;
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
        this.signupController = signupController;
        this.viewManager = viewManager;
        this.signupPanel = new SignupPanel(viewModel, signupController, viewManager);

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

