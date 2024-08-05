package view.home_page;

import interface_adaptors.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The HomePagePanel class represents the panel for the home page in the application.
 * It extends JPanel and is responsible for displaying the home page interface, including
 * buttons for login, signup, and exit, along with a background image.
 *
 * @author Dana
 * @author Eric
 */
public class HomePagePanel extends JPanel {
    private final HomePageViewModel viewModel;
    private final ViewManagerModel viewManager;
//    private ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JButton loginButton;
    private JButton signupButton;
    private JButton exitButton;
    private Image backgroundImage;

    /**
     * Constructs a HomePagePanel object with the specified view model and view manager.
     *
     * @param viewModel   the view model for the home page panel
     * @param viewManager the view manager for managing view transitions
     */
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

    /**
     * Loads the background image from the specified path.
     *
     * @param path the path to the image file
     */
    private void loadBackgroundImage(String path) {
        try {
            this.backgroundImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the component, including the background image.
     *
     * @param g the Graphics context to use for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Initializes the components used in the home page panel.
     */
    private void initializeComponents(){
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.signupButton = createButton(this.viewModel.getSignupButtonLabel());
        this.loginButton = createButton(this.viewModel.getLoginButtonLabel());
        this.exitButton = createButton(this.viewModel.getExitButtonLabel());
//
//        // Style buttons
//        this.loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
//        this.signupButton.setFont(new Font("Arial", Font.PLAIN, 16));
//        this.exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
//        this.loginButton.setBackground(new Color(255, 255, 255));
//        this.signupButton.setBackground(new Color(255, 255, 255));
//        this.exitButton.setBackground(new Color(255, 255, 255));
//        this.loginButton.setForeground(Color.BLACK);
//        this.signupButton.setForeground(Color.BLACK);
//        this.exitButton.setForeground(Color.BLACK);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(100, 30)); // Ensure all buttons have the same size
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(Color.BLACK);
        return button;
    }


    /**
     * Sets up the UI layout for the home page panel.
     */
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

    /**
     * Sets up listeners for the buttons in the home page panel.
     */
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
