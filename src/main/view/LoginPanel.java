package view;

import interface_adaptors.LoginController;
import interface_adaptors.LoginState;
import interface_adaptors.LoginViewModel;
import interface_adaptors.SignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel {
    private final LoginViewModel viewModel;
    private LoginController loginController;

    private JLabel titleLabel;
    private JTextField identificationTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginPanel(LoginViewModel viewModel, LoginController loginController) {
        this.loginController = loginController;
        this.viewModel = viewModel;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.identificationTextField = new JTextField(20);
        this.passwordField = new JPasswordField(20);

        JPanel buttons = new JPanel();
        this.loginButton = new JButton(this.viewModel.getLoginButtonLabel());
        buttons.add(this.loginButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButtonLabel());
        buttons.add(this.cancelButton);
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 10, 5, 10);  // pad

        // title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE; // This ensures the title label is not stretched horizontally
        add(titleLabel, constraints);

        // reset gridwidth and anchor for other components
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // identification
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getIdentificationLabel()), constraints);
        // input identification
        constraints.gridx = 1;
        add(identificationTextField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getPasswordLabel()), constraints);
        // input password
        constraints.gridx = 1;
        add(passwordField, constraints);

        // login button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(loginButton, constraints);

        // cancel button
        constraints.gridy = 4;
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
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });

        // get typed identification
        this.identificationTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        LoginState currentState = viewModel.getState();
                        currentState.setIdentification(identificationTextField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);

                        System.out.println("id: " + currentState.getIdentification());  //  debug
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
}
