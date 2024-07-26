package view.Signup;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The SignupPanel class represents the panel for user signup. It contains fields for user input and buttons for signup and cancel actions.
 *
 * @author Jessica
 * @author Eric
 */
public class SignupPanel extends JPanel {
    private final SignupViewModel viewModel;
    private SignupController signupController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextField idenficationField;
    private JButton signupButton;
    private JButton cancelButton;

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
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components for the signup panel.
     */
    private void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.usernameTextField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.idenficationField = new JTextField(20);

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
    }

    /**
     * Sets up the UI layout for the signup panel.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);  // pad

        // title Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
//        constraints.fill = GridBagConstraints.NONE; // This ensures the title label is not stretched horizontally
        add(titleLabel, constraints);

        // reset gridwidth and anchor for other components
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
//        constraints.fill = GridBagConstraints.HORIZONTAL;

        // username
//        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel(this.viewModel.getUsernameLabel()), constraints);
        // input username
        constraints.gridx = 1;
        add(this.usernameTextField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getPasswordLabel()), constraints);
        // input password
        constraints.gridx = 1;
        add(this.passwordField, constraints);

        // identification
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getID_LABEL()), constraints);
        // input identification
        constraints.gridx = 1;
        add(this.idenficationField, constraints);

        // sign up button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(this.signupButton, constraints);

        // cancel button
        constraints.gridy++;
        add(this.cancelButton, constraints);
    }

    /**
     * Sets up listeners for the signup and cancel buttons, and for key events in the text fields.
     */
    private void setupListeners() {
        // sign up button response action
        signupButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signupButton)) {
//                            SignupState currentState = viewModel.getState();
                            signupController.execute(
                                    usernameTextField.getText(),
                                    String.valueOf(passwordField.getPassword()),
                                    idenficationField.getText()
                            );
                        }
                    }
                }
        );

        // cancel button response action
        this.cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("home page");
        });

        // get typed username
        this.usernameTextField.addKeyListener(
                new KeyListener() {
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
                }
        );

        // get typed password
        this.passwordField.addKeyListener(
                new KeyListener() {
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
                }
        );

        this.idenficationField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        SignupState currentState = viewModel.getState();
                        currentState.setIdentification(idenficationField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

    }

    /**
     * Clears the text fields in the signup panel.
     */
    public void clearFields() {
        usernameTextField.setText("");
        passwordField.setText("");
        idenficationField.setText("");
    }
}
