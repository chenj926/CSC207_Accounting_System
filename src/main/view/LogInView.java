package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogInView extends JFrame implements PropertyChangeListener {
    private LoginViewModel viewModel;

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LogInView(LoginViewModel viewModel) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        titleLabel = new JLabel(viewModel.getTitleLabel());
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton(viewModel.getLoginButtonLabel());
        cancelButton = new JButton(viewModel.getCancelButtonLabel());

        setupUI();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 5, 2, 5);  // pad

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel(viewModel.getUsernameLabel()), constraints);

        constraints.gridx = 1;
        panel.add(usernameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel(viewModel.getPasswordLabel()), constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        constraints.gridy = 4;
        panel.add(cancelButton, constraints);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        loginButton.addActionListener(e -> {
            viewModel.getState().setUsername(usernameTextField.getText());
            viewModel.getState().setPassword(new String(passwordField.getPassword()));
            viewModel.firePropertyChanged();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            // Handle state changes if needed
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogInView(new LoginViewModel()));
    }
}
