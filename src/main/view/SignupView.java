package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class SignupView extends JFrame implements PropertyChangeListener {
    private SignupViewModel viewModel;

    // Declare UI components without initializing them yet
    private JLabel titleLabel;
    private JTextField usernameTextField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton signupButton;
    private JButton cancelButton;

    public SignupView(SignupViewModel viewModel) {
        super("Signup Form");
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Initialize dependent components here
        titleLabel = new JLabel(viewModel.getTitleLabel());
        signupButton = new JButton(viewModel.getSignupButtonLabel());
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
        panel.add(new JLabel(viewModel.getUsernameLabel()), constraints);

        constraints.gridx = 1;
        usernameTextField = new JTextField(20);
        panel.add(usernameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel(viewModel.getPasswordLabel()), constraints);

        constraints.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        signupButton = new JButton(viewModel.getSignupButtonLabel());
        panel.add(signupButton, constraints);

        cancelButton = new JButton(viewModel.getCancelButtonLabel());
        constraints.gridy++;
        panel.add(cancelButton, constraints);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }


    private void setupListeners() {
        signupButton.addActionListener(e -> {
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
            // Respond to state change
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new SignupView(new SignupViewModel()));
//    }
}
