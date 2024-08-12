package view.login.user_account;

import interface_adaptors.*;
import interface_adaptors.login.user_account.UserAccountLoginController;
import interface_adaptors.login.user_account.UserAccountLoginState;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The UserAccountLoginPanel class represents the panel used for user login.
 * It includes fields for user identification and password, as well as buttons for login and cancel actions.
 * It also handles setting up the user interface and listening to user inputs.
 *
 * @author Jessica
 * @author Eric
 */
public class UserAccountLoginPanel extends JPanel {
    private final UserAccountLoginViewModel viewModel;
    protected UserAccountLoginController loginController;
    private final ViewManagerModel viewManager;

    protected JLabel titleLabel;
    protected JTextField identificationTextField;
    protected JPasswordField passwordField;
    protected JButton loginButton;
    protected JButton cancelButton;


    /**
     * Constructs a UserAccountLoginPanel object with the specified view model, login controller, and view manager.
     *
     * @param viewModel        the view model for the login panel
     * @param loginController  the controller handling login actions
     * @param viewManager      the view manager for managing view transitions
     */
    public UserAccountLoginPanel(UserAccountLoginViewModel viewModel, UserAccountLoginController loginController, ViewManagerModel viewManager) {
        this.loginController = loginController;
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the components of the login panel, including labels, text fields, and buttons.
     * Styles the buttons with specific fonts and colors.
     */
    protected void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.identificationTextField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.identificationTextField.setToolTipText("Enter User Account ID");
        this.passwordField.setToolTipText("Enter User Account Password");

        // add buttons
        JPanel buttons = new JPanel();
        this.loginButton = new JButton(this.viewModel.getLoginButtonLabel());
        buttons.add(this.loginButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButtonLabel());
        buttons.add(this.cancelButton);

        // Style buttons
        this.loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.loginButton.setBackground(new Color(100, 150, 200));
        this.cancelButton.setBackground(new Color(200, 100, 100));
        this.loginButton.setForeground(Color.WHITE);
        this.cancelButton.setForeground(Color.WHITE);

        // adjust environment to compile MAC
        this.loginButton.setOpaque(true);
        this.loginButton.setBorderPainted(false);
        this.cancelButton.setOpaque(true);
        this.cancelButton.setBorderPainted(false);
    }

    /**
     * Sets up the user interface for the login panel, arranging components using a GridBagLayout.
     * Adds components such as labels, text fields, and buttons to the panel.
     */
    protected void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);  // pad

        // title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // reset gridwidth and anchor for other components
        constraints.anchor = GridBagConstraints.WEST;

        // identification
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(new JLabel(this.viewModel.getIdentificationLabel()), constraints);
        // input identification
        constraints.gridx = 1;
        add(this.identificationTextField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getPasswordLabel()), constraints);
        // input password
        constraints.gridx = 1;
        add(this.passwordField, constraints);


        // button panel for login and cancel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(this.loginButton);
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
     * Sets up listeners for user interactions, including button clicks and key presses.
     * Updates the view model based on user input and handles actions for login and cancel buttons.
     */
    protected void setupListeners() {
        // login in button response action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(loginButton)) {
                    loginController.execute(
                            identificationTextField.getText(),
                            String.valueOf(passwordField.getPassword())
//                            identificationTextField.getText()
                    );
                }
            }
        });

        // cancel button response action
        cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("home page");
        });

        // get typed identification
        this.identificationTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        UserAccountLoginState currentState = viewModel.getState();
                        currentState.setIdentification(identificationTextField.getText() + evt.getKeyChar());
                        viewManager.setUserId(identificationTextField.getText() + evt.getKeyChar());
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
                        UserAccountLoginState currentState = viewModel.getState();
                        currentState.setPassword(String.valueOf(passwordField.getPassword()) + evt.getKeyChar());
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
     * Clears the text fields for identification and password.
     */
    public void clearFields() {
        passwordField.setText("");
        identificationTextField.setText("");
    }
}
