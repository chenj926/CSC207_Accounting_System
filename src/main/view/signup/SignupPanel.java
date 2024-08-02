package view.signup;

import interface_adaptors.*;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupState;
import interface_adaptors.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The SignupPanel class represents the panel for user signup. It contains fields for user input and buttons for signup and cancel actions.
 */
public class SignupPanel extends JPanel implements PropertyChangeListener {
    protected final SignupViewModel viewModel;
    protected SignupController signupController;
    protected final ViewManagerModel viewManager;

    protected JLabel titleLabel;
    protected JTextField usernameTextField;
    protected JPasswordField passwordField;
    protected JTextField identificationField;
    protected JButton signupButton;
    protected JButton cancelButton;

    /**
     * Constructs a SignupPanel object with the specified view model, controller, and view manager.
     *
     * @param viewModel        the view model for the signup panel
     * @param signupController the controller for handling signup actions
     * @param viewManager      the view manager for managing view transitions
     */
    public SignupPanel(SignupViewModel viewModel, SignupController signupController, ViewManagerModel viewManager) {
        this.signupController = signupController;
        this.viewManager = viewManager;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this); // Listen for property changes

        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components for the signup panel.
     */
    protected void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.usernameTextField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.identificationField = new JTextField(20);

        // add buttons
        JPanel buttons = new JPanel();
        this.signupButton = new JButton(this.viewModel.getSignupButtonLabel());
        buttons.add(this.signupButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButtonLabel());
        buttons.add(this.cancelButton);

        // Style buttons
        this.signupButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.signupButton.setBackground(new Color(100, 150, 200));
        this.cancelButton.setBackground(new Color(200, 100, 100));
        this.signupButton.setForeground(Color.WHITE);
        this.cancelButton.setForeground(Color.WHITE);

        // adjust environment to compile MAC
        this.signupButton.setOpaque(true);
        this.signupButton.setBorderPainted(false);
        this.cancelButton.setOpaque(true);
        this.cancelButton.setBorderPainted(false);
    }

    /**
     * Sets up the UI layout for the signup panel.
     */
    protected void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // pad

        // title Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(titleLabel, constraints);

        // reset gridwidth and anchor for other components
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;

        // username
        constraints.gridy = 1;
        add(new JLabel(this.viewModel.getUsernameLabel()), constraints);
        constraints.gridx = 1;
        add(this.usernameTextField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getPasswordLabel()), constraints);
        constraints.gridx = 1;
        add(this.passwordField, constraints);

        // identification
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getID_LABEL()), constraints);
        constraints.gridx = 1;
        add(this.identificationField, constraints);

        // button panel for sign-up and cancel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(this.signupButton);
        buttonsPanel.add(this.cancelButton);

        // Add Buttons Panel
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(buttonsPanel, constraints);
    }

    /**
     * Sets up listeners for the signup and cancel buttons, and for key events in the text fields.
     */
    protected void setupListeners() {
        // sign up button response action
        signupButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        signupController.execute(
                                usernameTextField.getText(),
                                String.valueOf(passwordField.getPassword()),
                                identificationField.getText()
                        );
                    }
                }
        );

        // cancel button response action
        this.cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("home page");
        });

        // get typed username
        this.usernameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SignupState currentState = viewModel.getState();
                currentState.setUsername(usernameTextField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // get typed password
        this.passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SignupState currentState = viewModel.getState();
                currentState.setPassword(String.valueOf(passwordField.getPassword()) + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.identificationField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SignupState currentState = viewModel.getState();
                currentState.setIdentification(identificationField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    /**
     * Property change event handling for signup results.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();

        if (!state.isValid()) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            // Normal success message
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
            viewManager.setActiveViewName("home page");
        }
    }

    /**
     * Clears the text fields in the signup panel.
     */
    public void clearFields() {
        usernameTextField.setText("");
        passwordField.setText("");
        identificationField.setText("");
    }
}

