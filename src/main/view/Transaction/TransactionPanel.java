package view.Transaction;

import interface_adaptors.TransactionViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * The TransactionPanel class represents the panel used for displaying and managing transactions.
 * It provides buttons to navigate to different transaction views and displays current balance,
 * income, and outflow information.
 * <p>
 * This class extends JPanel and is responsible for setting up the user interface components
 * and handling user interactions.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class TransactionPanel extends JPanel {
    private final TransactionViewModel viewModel;
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
    //button
    private JButton oneTimeButton;
    private JButton periodicButton;
    private JButton logoutButton;

    /**
     * Constructs a TransactionPanel with the specified view model and view manager.
     *
     * @param viewModel  the view model for the transaction view
     * @param viewManager the view manager for handling view transitions
     */
    public TransactionPanel(TransactionViewModel viewModel, ViewManagerModel viewManager) {
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
        // title layout
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

        // Style buttons
        this.oneTimeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.periodicButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.oneTimeButton.setBackground(new Color(100, 150, 200));
        this.periodicButton.setBackground(new Color(100, 150, 200));
        this.logoutButton.setBackground(new Color(200, 100, 100));
        this.oneTimeButton.setForeground(Color.WHITE);
        this.periodicButton.setForeground(Color.WHITE);
        this.logoutButton.setForeground(Color.WHITE);
    }

    /**
     * Sets up the user interface by arranging the components in a GridBagLayout.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

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
        add(oneTimeButton, constraints);

        // Periodic Button
        constraints.gridx = 1;
        add(periodicButton, constraints);

        // Logout Button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(logoutButton, constraints);
    }

    /**
     * Sets up the listeners for the buttons to handle user actions.
     * <ul>
     * <li>The one-time button navigates to the one-time transaction view.</li>
     * <li>The periodic button navigates to the periodic transaction view.</li>
     * <li>The logout button resets the user ID and navigates back to the home page.</li>
     * </ul>
     */
    private void setupListeners() {
        // login button response action
        this.oneTimeButton.addActionListener(e -> this.viewManager.setActiveViewName("One Time Transaction"));

        // signup button response action
        this.periodicButton.addActionListener(e -> this.viewManager.setActiveViewName("Periodic Transaction"));

        // exit button response action
        this.logoutButton.addActionListener(e -> {

            viewManager.setUserId(null); // reset the userid before log out
            this.viewManager.setActiveViewName("home page"); // go back to home page
        });

    }
}

