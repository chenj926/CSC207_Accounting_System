package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class SignupView extends JFrame implements PropertyChangeListener {
    private SignupViewModel viewModel;

    private JLabel titleLabel = new JLabel(viewModel.TITLE_LABEL);

    // text size
    private JTextField usernameTextField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JPasswordField repeatPasswordField = new JPasswordField(20);

    // buttons
    private JButton signupButton = new JButton("Sign up");
    private JButton cancelButton = new JButton("Cancel");

    public SignupView(SignupViewModel viewModel) {
        super("Signup Form");
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

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

        // Adding components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);

        constraints.gridy++;
        panel.add(new JLabel("Choose username"), constraints);
        panel.add(usernameTextField, constraints);

        constraints.gridy++;
        panel.add(new JLabel("Choose password"), constraints);
        panel.add(passwordField, constraints);

        constraints.gridy++;
        panel.add(new JLabel("Enter password again"), constraints);
        panel.add(repeatPasswordField, constraints);

        constraints.gridy++;
        panel.add(signupButton, constraints);
        panel.add(cancelButton, constraints);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        signupButton.addActionListener(e -> {
            viewModel.getState().setUsername(usernameTextField.getText());
            viewModel.getState().setPassword(new String(passwordField.getPassword()));
            viewModel.getState().setRepeatPassword(new String(repeatPasswordField.getPassword()));
            viewModel.firePropertyChanged();
        });

        cancelButton.addActionListener(e -> {
            dispose(); // Close the window
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            // Here you can handle changes to the state, such as updating UI components based on new data
            // For example, handle error messages or updates
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupView(new SignupViewModel()));
    }
}
