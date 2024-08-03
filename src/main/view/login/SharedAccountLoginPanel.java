package view.login;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.SharedAccountLoginState;
import interface_adaptors.login.SharedAccountLoginController;
import interface_adaptors.login.SharedAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The SharedAccountLoginPanel class extends LoginPanel to handle shared account login.
 * It adds a field for entering the shared account ID and handles additional logic for shared account scenarios.
 */
public class SharedAccountLoginPanel extends JPanel { // Extend JPanel to use its methods
    private final SharedAccountLoginViewModel sharedViewModel;
    private final SharedAccountLoginController sharedLoginController;
    private final ViewManagerModel viewManager;
    private final JTextField sharedAccountIdField;
    private final JPasswordField sharedAccountPasswordField;

    protected JLabel titleLabel;
    protected JButton loginButton;
    protected JButton cancelButton;

    /**
     * Constructs a SharedAccountLoginPanel object with the specified view model, login controller, and view manager.
     *
     * @param sharedViewModel        the view model for the shared account login panel
     * @param sharedLoginController  the controller handling login actions
     * @param viewManager      the view manager for managing view transitions
     */
    public SharedAccountLoginPanel(SharedAccountLoginViewModel sharedViewModel,
                                   SharedAccountLoginController sharedLoginController,
                                   ViewManagerModel viewManager) {
        this.sharedViewModel = sharedViewModel;
        this.sharedLoginController = sharedLoginController;
        this.viewManager = viewManager;

        // Initialize components and UI
        this.sharedAccountIdField = new JTextField(20);
        this.sharedAccountPasswordField = new JPasswordField(20);

        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes additional components specific to the shared account login panel.
     */
    protected void initializeComponents() {
        this.titleLabel = new JLabel(sharedViewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add buttons
        JPanel buttons = new JPanel();
        this.loginButton = new JButton(this.sharedViewModel.getLoginButtonLabel());
        buttons.add(this.loginButton);
        this.cancelButton = new JButton(this.sharedViewModel.getCancelButtonLabel());
        buttons.add(this.cancelButton);

        // Style buttons
        this.loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.loginButton.setBackground(new Color(100, 150, 200));
        this.cancelButton.setBackground(new Color(200, 100, 100));
        this.loginButton.setForeground(Color.WHITE);
        this.cancelButton.setForeground(Color.WHITE);

        // Adjust environment to compile on macOS
        this.loginButton.setOpaque(true);
        this.loginButton.setBorderPainted(false);
        this.cancelButton.setOpaque(true);
        this.cancelButton.setBorderPainted(false);
    }

    /**
     * Sets up the user interface for the shared account login panel, arranging components using a GridBagLayout.
     * Adds components such as labels, text fields, and buttons to the panel.
     */
    protected void setupUI() {
        setLayout(new GridBagLayout()); // Ensure that the panel uses a GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // Padding

        // Title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // Reset gridwidth and anchor for other components
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;

        // Identification
        constraints.gridy++;
        add(new JLabel(this.sharedViewModel.getSharedAccountIdLabel()), constraints);

        // Input identification
        constraints.gridx = 1;
        add(this.sharedAccountIdField, constraints);

        // Password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.sharedViewModel.getSharedAccountPasswordLabel()), constraints);

        // Input password
        constraints.gridx = 1;
        add(this.sharedAccountPasswordField, constraints); // Added missing comma

        // Button panel for login and cancel
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
        // Login button response action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(loginButton)) {
                    sharedLoginController.execute(
                            String.valueOf(sharedAccountPasswordField.getPassword()),
                            sharedAccountIdField.getText()
                    );
                }
            }
        });

        // Cancel button response action
        cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("home page");
        });

        // Get typed identification
        this.sharedAccountIdField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountLoginState currentState = sharedViewModel.getState();
                currentState.setSharedAccountId(sharedAccountIdField.getText() + evt.getKeyChar());
                sharedViewModel.setState(currentState);
                viewManager.setSharedAccountId(sharedAccountIdField.getText() + evt.getKeyChar());
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Get typed password
        this.sharedAccountPasswordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountLoginState currentState = sharedViewModel.getState();
                currentState.setSharedAccountPassword(String.valueOf(sharedAccountPasswordField.getPassword()) + evt.getKeyChar());
                sharedViewModel.setState(currentState);
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    /**
     * Clears the text fields for identification, password, and shared account ID.
     */
    public void clearFields() {
        sharedAccountIdField.setText("");
        sharedAccountPasswordField.setText(""); // Clear shared account ID field
    }
}



