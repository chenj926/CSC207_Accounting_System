package view.transaction.one_time.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionState;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The SharedAccountOneTimeTransactionPanel class represents the user interface for a shared account one-time transaction form.
 * It extends JPanel and handles user inputs and interactions related to shared account one-time transactions.
 *
 */
public class SharedAccountOneTimeTransactionPanel extends JPanel {
    private final SharedAccountOneTimeTransactionViewModel viewModel;
    private final SharedAccountOneTimeTransactionController controller;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField amountField;
    private JTextField dateField;
    private JTextField descriptionField;
    private JComboBox<String> categoryComb;
    private JButton submitButton;
    private JButton cancelButton;
//    private JComboBox<String> selectUsersComb; // 可以做成comb让user select的形式，失策了
    private JTextField userIdField;

    /**
     * Constructs a SharedAccountOneTimeTransactionPanel object with the specified view model, controller, and view manager.
     *
     * @param viewModel  the view model for the shared account one-time transaction panel
     * @param controller the controller handling shared account one-time transaction actions
     * @param viewManager the view manager for handling view transitions
     */
    public SharedAccountOneTimeTransactionPanel(SharedAccountOneTimeTransactionViewModel viewModel,
                                                SharedAccountOneTimeTransactionController controller,
                                                ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the components for the shared account one-time transaction panel, including labels, text fields, and buttons.
     */
    private void initializeComponents() {
        // Title label
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Text fields
        this.amountField = new JTextField(20);
        this.dateField = new JTextField(20);
        this.descriptionField = new JTextField(20);
        this.userIdField = new JTextField(20);

        // Category combo box
        String[] categories = {"Auto", "Personal item", "Food", "Transport", "Income", "Entertainment", "Travel",
                "Utilities", "Medical", "Home", "Custom"};
        this.categoryComb = new JComboBox<>(categories);
//        String[] userIds = ;

        // Buttons
        this.submitButton = new JButton(viewModel.getSubmitButton());
        this.cancelButton = new JButton(viewModel.getCancelButton());
//        this.selectUsersComb = new JButton(viewModel.getSELECT_USERS_BUTTON_LABEL()); // Button to select users

        // Style buttons
        this.styleButton(this.submitButton, new Color(100, 150, 200));
        this.styleButton(this.cancelButton, new Color(200, 100, 100));
//        this.styleButton(this.selectUsersComb, new Color(100, 200, 100));
    }

    /**
     * Styles the given button with the specified background color.
     *
     * @param button the button to style
     * @param color the background color for the button
     */
    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    /**
     * Sets up the user interface layout for the shared account one-time transaction panel.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // Padding

        // Title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // Reset gridwidth and anchor for other components
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        // Select Users Button
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getSELECT_USERS_BUTTON_LABEL()), constraints);
        constraints.gridx = 1;
        add(this.userIdField, constraints);
        
        // Amount
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getAmount()), constraints);
        constraints.gridx = 1;
        add(this.amountField, constraints);

        // Date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDate()), constraints);
        constraints.gridx = 1;
        add(this.dateField, constraints);

        // Description
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDescription()), constraints);
        constraints.gridx = 1;
        add(this.descriptionField, constraints);

        // Category
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getCategoryButton()), constraints);
        constraints.gridx = 1;
        add(this.categoryComb, constraints);

        // Buttons panel for submit and cancel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(this.submitButton);
        buttonsPanel.add(this.cancelButton);

        // Add Buttons Panel
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(buttonsPanel, constraints);
    }

    /**
     * Sets up listeners for user interactions, including submit, cancel, and select users actions.
     */
    private void setupListeners() {
        // Submit button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(submitButton)) {
                    String category = (String) categoryComb.getSelectedItem();
                    if ("Custom".equals(category)) {
                        category = viewModel.getState().getTransactionCategory();
                    }

                    // Execute transaction with provided inputs
                    controller.execute(
                            amountField.getText(),
                            dateField.getText(),
                            descriptionField.getText(),
                            category,
                            viewManager.getUserId(), // Pass sharedAccountId from viewManager
                            userIdField.getText()
                    );
                }
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> viewManager.setActiveViewName("Shared Account Homepage Two"));

//        // Select Users button action
//        selectUsersComb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Open dialog to select responsible users
//                String users = JOptionPane.showInputDialog(
//                        null, "Enter responsible user IDs separated by commas",
//                        "Select Users", JOptionPane.PLAIN_MESSAGE);
//
//                if (users != null && !users.trim().isEmpty()) {
//                    // Split input string and add to set
//                    String[] userIds = users.split(",");
//                    for (String userId : userIds) {
//                        responsibleUserIds.add(userId.trim());
//                    }
//                    JOptionPane.showMessageDialog(null, "Selected Users: " + responsibleUserIds);
//                }
//            }
//        });

        // Add listeners for typing in fields to update state
        addTypingListeners();
    }

    /**
     * Adds typing listeners to fields to update the transaction state.
     */
    private void addTypingListeners() {
        // Amount field listener
        this.amountField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountOneTimeTransactionState currentState = viewModel.getState();
                currentState.setTransactionAmount(amountField.getText() + evt.getKeyChar());
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Date field listener
        this.dateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountOneTimeTransactionState currentState = viewModel.getState();
                currentState.setTransactionDate(dateField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Description field listener
        this.descriptionField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                SharedAccountOneTimeTransactionState currentState = viewModel.getState();
                currentState.setTransactionDescription(descriptionField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Category combo box action
        this.categoryComb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SharedAccountOneTimeTransactionState currentState = viewModel.getState();

                // Check if the selected item is "Custom"
                if ("Custom".equals(categoryComb.getSelectedItem())) {
                    // Prompt the user to enter a custom category
                    String input = JOptionPane.showInputDialog(
                            null, "Enter the custom category",
                            "Custom Category", JOptionPane.PLAIN_MESSAGE);

                    // Set the custom category
                    currentState.setTransactionCategory(input);
                    viewModel.setState(currentState);
                } else {
                    // Set the selected category
                    currentState.setTransactionCategory((String) categoryComb.getSelectedItem());
                    viewModel.setState(currentState);
                }
            }
        });
    }

    /**
     * Clears all input fields in the shared account one-time transaction panel.
     */
    public void clearFields() {
        amountField.setText("");
        dateField.setText("");
        descriptionField.setText("");
        categoryComb.setSelectedIndex(0);
//        responsibleUserIds.clear(); // Clear responsible user IDs
    }
}




