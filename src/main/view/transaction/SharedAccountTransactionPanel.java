package view.transaction;

import interface_adaptors.transaction.SharedAccountTransactionViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * The SharedAccountTransactionPanel class represents the panel used for displaying and managing shared account transactions.
 * It provides buttons to navigate to different transaction views and displays current balance,
 * income, and outflow information, along with an option to add users to a shared account.
 * <p>
 * This class extends JPanel and is responsible for setting up the user interface components
 * and handling user interactions.
 * </p>
 */
public class SharedAccountTransactionPanel extends JPanel {
    private final SharedAccountTransactionViewModel viewModel;
    private final ViewManagerModel viewManager;

    // text
    private JLabel titleLabel;
    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel outflowLabel;
    // value
    private JLabel balanceValueLabel;
    private JLabel incomeValueLabel;
    private JLabel outflowValueLabel;
    // buttons
    private JButton oneTimeButton;
    private JButton periodicButton;
    private JButton logoutButton;
    private JButton financialReportButton;
    private JButton addUserButton; // Button to add user to shared account

    /**
     * Constructs a SharedAccountTransactionPanel with the specified view model and view manager.
     *
     * @param viewModel  the view model for the shared account transaction view
     * @param viewManager the view manager for handling view transitions
     */
    public SharedAccountTransactionPanel(SharedAccountTransactionViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components for the panel.
     * This includes labels for title, balance, income, and outflow, and buttons for navigation.
     */
    private void initializeComponents() {
        // Title layout
        this.titleLabel = new JLabel(this.viewModel.getTITLE_LABEL());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.balanceLabel = new JLabel(this.viewModel.getBALANCE_LABEL());
        this.incomeLabel = new JLabel(this.viewModel.getINCOME_LABEL());
        this.outflowLabel = new JLabel(this.viewModel.getOUTFLOW_LABEL());

        this.balanceValueLabel = new JLabel("0.00");
        this.incomeValueLabel = new JLabel("0.00");
        this.outflowValueLabel = new JLabel("0.00");

        JPanel buttons = new JPanel();
        this.oneTimeButton = new JButton(this.viewModel.getONE_TIME_BUTTON_LABEL());
        buttons.add(this.oneTimeButton);
        this.periodicButton = new JButton(this.viewModel.getPERIODIC_BUTTON_LABEL());
        buttons.add(this.periodicButton);
        this.logoutButton = new JButton(this.viewModel.getCANCEL_BUTTON_LABEL());
        buttons.add(this.logoutButton);
        this.financialReportButton = new JButton(this.viewModel.getFINANCIAL_REPORT_BUTTON_LABEL());
        buttons.add(this.financialReportButton);

        // Initialize the add user button
        this.addUserButton = new JButton("Add User to Shared Account");
        buttons.add(this.addUserButton);

        // Style buttons
        this.oneTimeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.periodicButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.financialReportButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.addUserButton.setFont(new Font("Arial", Font.PLAIN, 16));

        this.oneTimeButton.setBackground(new Color(100, 150, 200));
        this.periodicButton.setBackground(new Color(100, 150, 200));
        this.logoutButton.setBackground(new Color(200, 100, 100));
        this.financialReportButton.setBackground(new Color(150, 200, 100));
        this.addUserButton.setBackground(new Color(150, 150, 150));

        this.oneTimeButton.setForeground(Color.WHITE);
        this.periodicButton.setForeground(Color.WHITE);
        this.logoutButton.setForeground(Color.WHITE);
        this.financialReportButton.setForeground(Color.WHITE);
        this.addUserButton.setForeground(Color.WHITE);

        // Adjust environment to compile MAC
        this.oneTimeButton.setOpaque(true);
        this.oneTimeButton.setBorderPainted(false);
        this.periodicButton.setOpaque(true);
        this.periodicButton.setBorderPainted(false);
        this.logoutButton.setOpaque(true);
        this.logoutButton.setBorderPainted(false);
        this.financialReportButton.setOpaque(true);
        this.financialReportButton.setBorderPainted(false);
        this.addUserButton.setOpaque(true);
        this.addUserButton.setBorderPainted(false);
    }

    /**
     * Sets up the user interface by arranging the components in a GridBagLayout.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Set the preferred size for buttons
        Dimension buttonSize = new Dimension(200, 30);  // Adjust size as needed
        this.oneTimeButton.setPreferredSize(buttonSize);
        this.periodicButton.setPreferredSize(buttonSize);
        this.logoutButton.setPreferredSize(buttonSize);
        this.financialReportButton.setPreferredSize(buttonSize);
        this.addUserButton.setPreferredSize(buttonSize);

        // Title Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // Balance Label and Field
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(this.balanceLabel, constraints);
        constraints.gridx = 1;
        add(this.balanceValueLabel, constraints);

        // Income Label and Field
        constraints.gridx = 0;
        constraints.gridy++;
        add(incomeLabel, constraints);
        constraints.gridx = 1;
        add(this.incomeValueLabel, constraints);

        // Outflow Label and Field
        constraints.gridx = 0;
        constraints.gridy++;
        add(outflowLabel, constraints);
        constraints.gridx = 1;
        add(this.outflowValueLabel, constraints);

        // One Time Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        add(this.oneTimeButton, constraints);

        // Periodic Button
        constraints.gridx = 1;
        add(this.periodicButton, constraints);

        // Add User Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.addUserButton, constraints);

        // Financial Report Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.financialReportButton, constraints);

        // Logout Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.logoutButton, constraints);
    }

    /**
     * Sets up the listeners for the buttons to handle user actions.
     * <ul>
     * <li>The one-time button navigates to the one-time transaction view.</li>
     * <li>The periodic button navigates to the periodic transaction view.</li>
     * <li>The logout button resets the user ID and navigates back to the home page.</li>
     * <li>The add user button allows adding a user to the shared account.</li>
     * </ul>
     */
    private void setupListeners() {
        // One-time transaction button response action
        this.oneTimeButton.addActionListener(e -> this.viewManager.setActiveViewName("Shared Account One Time Transaction"));

        // Periodic transaction button response action
        this.periodicButton.addActionListener(e -> this.viewManager.setActiveViewName("Shared Account Periodic Transaction"));

        // Financial report button response action
        this.financialReportButton.addActionListener(e -> this.viewManager.setActiveViewName("Financial Report"));

        // Add user button response action
        this.addUserButton.addActionListener(e -> {
            // Implement logic to add user to shared account
            JOptionPane.showMessageDialog(this, "Adding user to shared account...");
            // Add actual logic to add user as per the application's requirement
        });

        // Logout button response action
        this.logoutButton.addActionListener(e -> {
            viewManager.setUserId(null); // reset the user ID before logout
            this.viewManager.setActiveViewName("home page"); // go back to the home page
        });
    }
}

