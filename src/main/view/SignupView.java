package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class SignupView extends JFrame implements PropertyChangeListener {
    private SignupViewModel viewModel;

    // title
    private JLabel titleLabel = new JLabel(viewModel.getTitleLabel());

    // text size
    private JTextField usernameTextField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JPasswordField repeatPasswordField = new JPasswordField(20);

    // buttons
    private JButton signupButton = new JButton(viewModel.getSignupButtonLabel());
    private JButton cancelButton = new JButton(viewModel.getCancelButtonLabel());

    public SignupView(SignupViewModel viewModel) {
        super(viewModel.getSignupButtonLabel());
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

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);

        constraints.gridy++;
        panel.add(new JLabel(viewModel.getUsernameLabel()), constraints);
        panel.add(usernameTextField, constraints);

        constraints.gridy++;
        panel.add(new JLabel(viewModel.getPasswordLabel()), constraints);
        panel.add(passwordField, constraints);

        constraints.gridy++;
        panel.add(signupButton, constraints);
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
            dispose(); // close window
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
