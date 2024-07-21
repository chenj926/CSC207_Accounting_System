package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel {
    private final LoginViewModel viewModel;
    private LoginController loginController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField identificationTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginPanel(LoginViewModel viewModel, LoginController loginController, ViewManagerModel viewManager) {
        this.loginController = loginController;
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.identificationTextField = new JTextField(20);
        this.passwordField = new JPasswordField(20);

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
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);  // pad

        // title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
//        constraints.fill = GridBagConstraints.NONE; // This ensures the title label is not stretched horizontally
        add(this.titleLabel, constraints);

        // reset gridwidth and anchor for other components
//        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
//        constraints.fill = GridBagConstraints.HORIZONTAL;

        // identification
//        constraints.gridx = 0;
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

        // login button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(loginButton, constraints);

        // cancel button
        constraints.gridy++;
        add(cancelButton, constraints);
    }

    private void setupListeners() {
        // login in button response action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(loginButton)) {
                    loginController.execute(
                            String.valueOf(passwordField.getPassword()),
                            identificationTextField.getText()
                    );

                    // debug
                    System.out.println("login controller: " + loginController.toString());
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
                        LoginState currentState = viewModel.getState();
                        currentState.setIdentification(identificationTextField.getText() + evt.getKeyChar());
                        viewManager.setUserId(identificationTextField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);

                        System.out.println("id: " + currentState.getIdentification());  //  debug
                        System.out.println("vmr id: " + viewManager.getUserId());
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
                        LoginState currentState = viewModel.getState();
                        currentState.setPassword(String.valueOf(passwordField.getPassword()) + evt.getKeyChar());
                        viewModel.setState(currentState);

                        System.out.println("pass: " + currentState.getPassword());  //  debug
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );
    }

    public void clearFields() {
        passwordField.setText("");
        identificationTextField.setText("");
    }
}
