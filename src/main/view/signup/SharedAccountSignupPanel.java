package view.signup;

import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SharedAccountSignupState;
import interface_adaptors.signup.SharedAccountSignupViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

/**
 * The SharedAccountSignupPanel class extends SignupPanel to add additional fields and logic
 * specific to shared account signup.
 */
public class SharedAccountSignupPanel extends SignupPanel {

    private JTextField sharedAccountIdField;
    private SharedAccountSignupViewModel viewModel; // Specific view model for shared accounts

    /**
     * Constructs a SharedAccountSignupPanel object with the specified view model, controller, and view manager.
     *
     * @param viewModel        the view model for the signup panel
     * @param signupController the controller for handling signup actions
     * @param viewManager      the view manager for managing view transitions
     */
    public SharedAccountSignupPanel(SharedAccountSignupViewModel viewModel, SignupController signupController, ViewManagerModel viewManager) {
        super(viewModel, signupController, viewManager);
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);// Initialize the specific shared account view model

        initializeComponents();  // Ensure components are initialized
        setupUI();  // Set up the UI
        setupListeners();
    }

    /**
     * Initializes the UI components for the shared account signup panel.
     */
    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        this.sharedAccountIdField = new JTextField(20); // Field for shared account ID
    }

    /**
     * Sets up the UI layout for the shared account signup panel.
     */
    @Override
    protected void setupUI() {
        super.setupUI(); // Call the parent setupUI to ensure base components are added

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // Padding
        constraints.gridx = 0;
        constraints.gridy = 3; // Row index

        // Label for Shared Account ID
        JLabel sharedAccountLabel = new JLabel(this.viewModel.getSharedAccountIdLabel());
        sharedAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(sharedAccountLabel, constraints);

        constraints.gridx = 1; // Column index
        add(this.sharedAccountIdField, constraints);
    }


    /**
     * Sets up listeners for the signup and cancel buttons, and for key events in the text fields.
     */
    @Override
    protected void setupListeners() {
        super.setupListeners();
        signupButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        signupController.execute(
                                usernameTextField.getText(),
                                String.valueOf(passwordField.getPassword()),
                                identificationField.getText(),
                                sharedAccountIdField.getText()
                        );
                    }
                }
        );

        // get typed shared account ID
        this.sharedAccountIdField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountSignupState currentState = viewModel.getState();
                currentState.setSharedAccountId(sharedAccountIdField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    /**
     * Property change event handling specific for shared account signup results.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountSignupState state = (SharedAccountSignupState) evt.getNewValue();

        if (!state.isValid()) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            String successMsg = state.getSuccessMsg();

            if (successMsg.contains("Shared account already exists")) {
                // Show choice dialog for shared account existing case
                int choice = JOptionPane.showOptionDialog(
                        this,
                        "Shared account already exists. Would you like to add to it or create a new shared account?",
                        "Choose Action",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Add to Existing", "Create New"},
                        "Add to Existing"
                );

                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, "Added to shared account successfully.");
                    // Handle adding logic here
                } else {
                    JOptionPane.showMessageDialog(this, "Create a new shared account.");
                    // Handle creation logic here
                }
            } else {
                // Normal success message
                JOptionPane.showMessageDialog(this, successMsg);
                viewManager.setActiveViewName("home page");
            }
        }
    }

    /**
     * Clears the text fields in the shared account signup panel.
     */
    @Override
    public void clearFields() {
        super.clearFields();
        sharedAccountIdField.setText("");
    }
}
