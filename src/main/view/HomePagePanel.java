package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HomePagePanel extends JPanel {
    private final HomePageViewModel viewModel;

    private JLabel titleLabel;
    private JButton loginButton;
    private JButton signupButton;
    private JButton exitButton;

    public HomePagePanel(HomePageViewModel viewModel) {
        this.viewModel = viewModel;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents(){
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());

        JPanel buttons = new JPanel();
        this.loginButton = new JButton(this.viewModel.getLoginButtonLabel());
        buttons.add(this.loginButton);
        this.signupButton = new JButton(this.viewModel.getSignupButtonLabel());
        buttons.add(this.signupButton);
        this.exitButton = new JButton(this.viewModel.getExitButtonLabel());
        buttons.add(this.exitButton);

    }
    private void setupUI(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 5, 2, 5);

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

        // login button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(loginButton, constraints);

        // sign up button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(this.signupButton, constraints);

        // exit button
        constraints.gridy++;
        add(this.exitButton, constraints);
    }
    private void setupListeners(){
        // signup button response action
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        // exit button response action
        this.exitButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });

    }

}
