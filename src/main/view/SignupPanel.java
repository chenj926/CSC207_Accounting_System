package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {
    private final SignupViewModel viewModel;

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton signupButton;
    private JButton cancelButton;

    public SignupPanel(SignupViewModel viewModel) {
        this.viewModel = viewModel;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        titleLabel = new JLabel(viewModel.getTitleLabel());
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        signupButton = new JButton(viewModel.getSignupButtonLabel());
        cancelButton = new JButton(viewModel.getCancelButtonLabel());
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 5, 2, 5);  // pad

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel(viewModel.getUsernameLabel()), constraints);

        constraints.gridx = 1;
        add(usernameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getPasswordLabel()), constraints);

        constraints.gridx = 1;
        add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(signupButton, constraints);

        constraints.gridy++;
        add(cancelButton, constraints);
    }

    private void setupListeners() {
        signupButton.addActionListener(e -> {
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
