package view;

import interface_adaptors.LoginViewModel;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private LoginViewModel viewModel;

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginPanel(LoginViewModel viewModel) {
        this.viewModel = viewModel;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        titleLabel = new JLabel(viewModel.getTitleLabel());
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton(viewModel.getLoginButtonLabel());
        cancelButton = new JButton(viewModel.getCancelButtonLabel());
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 5, 2, 5);  // pad

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(titleLabel, constraints);

        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel(viewModel.getUsernameLabel()), constraints);

        constraints.gridx = 1;
        add(usernameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(new JLabel(viewModel.getPasswordLabel()), constraints);

        constraints.gridx = 1;
        add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(loginButton, constraints);

        constraints.gridy = 4;
        add(cancelButton, constraints);
    }

    private void setupListeners() {
        loginButton.addActionListener(e -> {
            viewModel.getState().setUsername(usernameTextField.getText());
            viewModel.getState().setPassword(new String(passwordField.getPassword()));
            viewModel.firePropertyChanged();
        });

        cancelButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });
    }
}
