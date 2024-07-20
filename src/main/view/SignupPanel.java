package view;

import interface_adaptors.*;
import use_case.SignupInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignupPanel extends JPanel {
    private final SignupViewModel viewModel;
    private SignupController signupController;

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextField idenficationField;
    private JButton signupButton;
    private JButton cancelButton;

    public SignupPanel(SignupViewModel viewModel, SignupController signupController) {
        this.signupController = signupController;
        this.viewModel = viewModel;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.usernameTextField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.idenficationField = new JTextField(20);

        JPanel buttons = new JPanel();
        this.signupButton = new JButton(this.viewModel.getSignupButtonLabel());
        buttons.add(this.signupButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButtonLabel());
        buttons.add(this.cancelButton);
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 10, 5, 10);  // pad

        // title Label
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

        // username
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel(this.viewModel.getUsernameLabel()), constraints);
        // input username
        constraints.gridx = 1;
        add(this.usernameTextField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getPasswordLabel()), constraints);
        // input password
        constraints.gridx = 1;
        add(this.passwordField, constraints);

        // identification
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(this.viewModel.getID_LABEL()), constraints);
        // input identification
        constraints.gridx = 1;
        add(this.idenficationField, constraints);

        // sign up button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(this.signupButton, constraints);

        // cancel button
        constraints.gridy++;
        add(this.cancelButton, constraints);
    }

    private void setupListeners() {
        // sign up button response action
        signupButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signupButton)) {
//                            SignupState currentState = viewModel.getState();
                            signupController.execute(
                                    usernameTextField.getText(),
                                    String.valueOf(passwordField.getPassword()),
                                    idenficationField.getText()
                            );

                            // debug
                            System.out.println(signupController);
                        }
                    }
                }
        );

        // cancel button response action
        this.cancelButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });

        // get typed username
        this.usernameTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        SignupState currentState = viewModel.getState();
                        currentState.setUsername(usernameTextField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);

                        System.out.println("username: " + currentState.getUsername());  //  debug
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
                        SignupState currentState = viewModel.getState();
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

        this.idenficationField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        SignupState currentState = viewModel.getState();
                        currentState.setIdentification(idenficationField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);

                        System.out.println("id: " + currentState.getIdentification());  //  debug
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

    }
}
