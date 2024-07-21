package view;

import interface_adaptors.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HomePagePanel extends JPanel {
    private final HomePageViewModel viewModel;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JButton loginButton;
    private JButton signupButton;
    private JButton exitButton;
    private Image backgroundImage;

    public HomePagePanel(HomePageViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;

        String baseDir = System.getProperty("user.dir");
        Path imgPath = Paths.get(baseDir, "src/main/UI_img/homePage3.png");
        loadBackgroundImage(String.valueOf(imgPath));
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void loadBackgroundImage(String path) {
        try {
            this.backgroundImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initializeComponents(){
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.signupButton = new JButton(this.viewModel.getSignupButtonLabel());
        this.loginButton = new JButton(this.viewModel.getLoginButtonLabel());
        this.exitButton = new JButton(this.viewModel.getExitButtonLabel());

        // Style buttons
        this.loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.signupButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.loginButton.setBackground(new Color(255, 255, 255));
        this.signupButton.setBackground(new Color(255, 255, 255));
        this.exitButton.setBackground(new Color(255, 255, 255));
        this.loginButton.setForeground(Color.BLACK);
        this.signupButton.setForeground(Color.BLACK);
        this.exitButton.setForeground(Color.BLACK);
    }

    private void setupUI(){
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);  // Make the panel transparent
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(loginButton, constraints);

        constraints.gridy++;
        centerPanel.add(signupButton, constraints);

        constraints.gridy++;
        centerPanel.add(exitButton, constraints);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
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
