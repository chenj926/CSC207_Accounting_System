package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPanel extends JPanel {
    private final SignupViewModel viewModel;

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton signupButton;
    private JButton cancelButton;

    private SignupController signupController;

    public SignupPanel(SignupViewModel viewModel) {
        this.viewModel = viewModel;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        JPanel buttons = new JPanel();
        titleLabel = new JLabel(viewModel.getTitleLabel());
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        signupButton = new JButton(viewModel.getSignupButtonLabel());
        buttons.add(signupButton);
        cancelButton = new JButton(viewModel.getCancelButtonLabel());
        buttons.add(cancelButton);
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
        // sign up button response action
        signupButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signupButton)) {
                            SignupState currentState = viewModel.getState();
                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getIdentification()
                            );
                        }
                    }
                }
        );

        // cancel button response action
        cancelButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });
    }
}
