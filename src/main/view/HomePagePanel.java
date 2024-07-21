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
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JButton loginButton;
    private JButton signupButton;
    private JButton exitButton;

    public HomePagePanel(HomePageViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents(){
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttons = new JPanel();
        this.loginButton = new JButton(this.viewModel.getLoginButtonLabel());
        buttons.add(this.loginButton);
        this.signupButton = new JButton(this.viewModel.getSignupButtonLabel());
        buttons.add(this.signupButton);
        this.exitButton = new JButton(this.viewModel.getExitButtonLabel());
        buttons.add(this.exitButton);

        // Style buttons
        this.loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.signupButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.loginButton.setBackground(new Color(100, 150, 200));
        this.signupButton.setBackground(new Color(100, 150, 200));
        this.exitButton.setBackground(new Color(100, 150, 200));
        this.loginButton.setForeground(Color.WHITE);
        this.signupButton.setForeground(Color.WHITE);
        this.exitButton.setForeground(Color.WHITE);
    }

    private void setupUI(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // title Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
//        constraints.fill = GridBagConstraints.NONE; // This ensures the title label is not stretched horizontally
        add(titleLabel, constraints);

        // reset gridwidth and anchor for other components
//        constraints.gridwidth = 1;
//        constraints.anchor = GridBagConstraints.WEST;
//        constraints.fill = GridBagConstraints.HORIZONTAL;

        // login button
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(loginButton, constraints);

        // sign up button
        constraints.gridy++;
        add(this.signupButton, constraints);

        // exit button
        constraints.gridy++;
        add(this.exitButton, constraints);
    }

    private void setupListeners(){
        // login button response action
        loginButton.addActionListener(e -> this.viewManager.setActiveViewName("log in"));

        // signup button response action
        signupButton.addActionListener(e -> this.viewManager.setActiveViewName("sign up"));

        // exit button response action
        this.exitButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
            // Explicitly terminate the application
            System.exit(0);
        });

    }

}
