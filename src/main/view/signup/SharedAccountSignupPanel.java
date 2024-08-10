package view.signup;

import interface_adaptors.signup.SharedAccountSignupController;
import interface_adaptors.signup.SharedAccountSignupViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SharedAccountSignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The SharedAccountSignupPanel class handles UI for shared account signup, allowing for dynamic user field addition.
 */
public class SharedAccountSignupPanel extends JPanel implements PropertyChangeListener {
    protected final SharedAccountSignupViewModel viewModel;
    protected final SharedAccountSignupController signupController;
    protected final ViewManagerModel viewManager;

    protected JLabel titleLabel;
    protected JTextField sharedAccountIdField;
    protected JPasswordField sharedAccountPasswordField;
    protected JTextField user1IdField;
    protected JTextField user2IdField;
    protected JButton signupButton;
    protected JButton cancelButton;
    protected JButton addUserButton;
    protected JButton deleteUserButton;

    protected JPanel additionalUsersPanel;
    protected Set<JTextField> additionalUserFields;

    /**
     * Constructs a SharedAccountSignupPanel object with the specified view model, controller, and view manager.
     *
     * @param viewModel              the view model for the signup panel
     * @param sharedSignupController the controller for handling signup actions
     * @param viewManager            the view manager for managing view transitions
     */
    public SharedAccountSignupPanel(SharedAccountSignupViewModel viewModel, SharedAccountSignupController sharedSignupController, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.signupController = sharedSignupController;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);

        initializeComponents();  // Ensure components are initialized
        setupUI();  // Set up the UI
        setupListeners();  // Set up event listeners
    }

    /**
     * Initializes the UI components for the shared account signup panel.
     */
    protected void initializeComponents() {
        titleLabel = new JLabel(viewModel.getTitleLabel());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        sharedAccountIdField = new JTextField(20);
        sharedAccountPasswordField = new JPasswordField(20);
        user1IdField = new JTextField(20);
        user2IdField = new JTextField(20);

        // tip explanation to users
        this.sharedAccountIdField.setToolTipText("Set a nick-name for your account");
        this.sharedAccountPasswordField.setToolTipText("Set a secret password");
        this.user1IdField.setToolTipText("Enter at least 2 or more user account id to create a shared account between users");

        signupButton = new JButton(viewModel.getSignupButtonLabel());
        cancelButton = new JButton(viewModel.getCancelButtonLabel());
        addUserButton = new JButton("+");
        deleteUserButton = new JButton("-");

        additionalUsersPanel = new JPanel(new GridBagLayout());
        additionalUserFields = new LinkedHashSet<>();  // Use LinkedHashSet to maintain insertion order
    }

    /**
     * Sets up the UI layout for the shared account signup panel.
     */
    protected void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // Padding

        // Title
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(titleLabel, constraints);

        // Shared Account ID
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(new JLabel(viewModel.getID_LABEL()), constraints);
        constraints.gridx = 1;
        add(sharedAccountIdField, constraints);

        // Shared Account Password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getPasswordLabel()), constraints);
        constraints.gridx = 1;
        add(sharedAccountPasswordField, constraints);

        // User 1 ID
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getUserIdsLabel()), constraints);
        constraints.gridx = 1;
        add(user1IdField, constraints);

        // User 2 ID
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(""), constraints);
        constraints.gridx = 1;
        add(user2IdField, constraints);

        // Additional Users Panel
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(additionalUsersPanel, constraints);

        // Buttons
        constraints.gridy++;
        constraints.gridwidth = 2;
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.add(signupButton);
        buttonsPanel.add(cancelButton);
        add(buttonsPanel, constraints);

        updateAdditionalUsersPanel();  // Initial panel update
    }

    /**
     * Sets up listeners for the signup, add user, and delete user buttons, and for key events in the text fields.
     */
    protected void setupListeners() {
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                // 所有id都封装成一个set
                Set<String> userIds = new LinkedHashSet<>();
                // add the 1st 2 user id inputs
                userIds.add(user1IdField.getText());
                userIds.add(user2IdField.getText());
                for (JTextField userField : additionalUserFields) {
                    userIds.add(userField.getText());
                }

                signupController.execute(
                        sharedAccountIdField.getText(),
                        String.valueOf(sharedAccountPasswordField.getPassword()),
                        userIds
                );
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                viewModel.addMoreUserLabel();
                JTextField newUserField = new JTextField(20);
                additionalUserFields.add(newUserField);
                updateAdditionalUsersPanel();
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!additionalUserFields.isEmpty()) {
                    JTextField lastUserField = null;
                    for (JTextField userField : additionalUserFields) {
                        lastUserField = userField;
                    }
                    if (lastUserField != null) {
                        additionalUserFields.remove(lastUserField);
//                        viewModel.removeLastUserLabel();
                        updateAdditionalUsersPanel();
                    }
                }
            }
        });

        // cancel button response action
        this.cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("home page");
        });

        // Shared Account ID Key Listener
        this.sharedAccountIdField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountSignupState currentState = viewModel.getState();
                currentState.setIdentification(sharedAccountIdField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    /**
     * Updates the panel for additional user fields.
     */
    private void updateAdditionalUsersPanel() {
        additionalUsersPanel.removeAll();
        GridBagConstraints userConstraints = new GridBagConstraints();
        userConstraints.insets = new Insets(5, 5, 5, 5);

        int row = 0;
        for (JTextField userField : additionalUserFields) {
            userConstraints.gridx = 0;
            userConstraints.gridy = row;
            userConstraints.gridwidth = 1;
            additionalUsersPanel.add(new JLabel("User ID " + (row + 3) + ":"), userConstraints);

            userConstraints.gridx = 1;
            additionalUsersPanel.add(userField, userConstraints);

            userConstraints.gridx = 2;
            additionalUsersPanel.add(deleteUserButton, userConstraints);

            row++;
        }

        // Add the "+" button at the end
        userConstraints.gridx = 0;
        userConstraints.gridy = row;
        userConstraints.gridwidth = 2;
        additionalUsersPanel.add(addUserButton, userConstraints);

        // Hide delete button if only 2 user fields left
        deleteUserButton.setVisible(!additionalUserFields.isEmpty());

        additionalUsersPanel.revalidate();
        additionalUsersPanel.repaint();
    }

    /**
     * Property change event handling specific for shared account signup results.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountSignupState state = (SharedAccountSignupState) evt.getNewValue();

        if (!state.isComplete()) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            // Normal success message
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
            viewManager.setActiveViewName("home page");
        }
    }

    /**
     * Clears the text fields in the shared account signup panel.
     */
    public void clearFields() {
        sharedAccountIdField.setText("");
        sharedAccountPasswordField.setText("");
        user1IdField.setText("");
        user2IdField.setText("");
        additionalUserFields.clear();
        updateAdditionalUsersPanel();
    }
}


