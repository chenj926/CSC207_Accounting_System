package view.transaction.periodic;

import interface_adaptors.*;
import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import interface_adaptors.transaction.periodic.PeriodicTransactionState;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The PeriodicTransactionPanel class represents the panel used to input and manage periodic transactions.
 * It includes fields for transaction amount, start and end dates, description, and period, along with
 * submit and cancel buttons.
 *
 * @author Eric
 * @author Jessica
 */
public class PeriodicTransactionPanel extends JPanel {
    private final PeriodicTransactionViewModel viewModel;
    private final PeriodicTransactionController periodicTransactionController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField amountField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField descriptionField;
//    private JTextField periodField;
    // add drop down manu for user to choose period units(day, month, year)
    private JComboBox<String> periodComb;
    private JButton submitButton;
    private JButton cancelButton;

    /**
     * Constructs a PeriodicTransactionPanel with the specified view model, controller, and view manager.
     *
     * @param viewModel                   the view model for the periodic transaction panel
     * @param periodicTransactionController the controller handling periodic transaction actions
     * @param viewManager                 the view manager for handling view transitions
     */
    public PeriodicTransactionPanel(PeriodicTransactionViewModel viewModel,
                                    PeriodicTransactionController periodicTransactionController,
                                    ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.periodicTransactionController = periodicTransactionController;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the components for the panel, including labels, text fields, and buttons.
     */
    private void initializeComponents() {
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.amountField = new JTextField(20);
        this.startDateField = new JTextField(20);
        this.endDateField = new JTextField(20);
        this.descriptionField = new JTextField(20);

        // initialize period drop downs
        String[] periods = {"day", "week", "month", "year", "custom"};
        this.periodComb = new JComboBox<>(periods);

        JPanel buttons = new JPanel();
        this.submitButton = new JButton(this.viewModel.getSubmitButton());
        buttons.add(this.submitButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButton());
        buttons.add(this.cancelButton);

        this.submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.submitButton.setBackground(new Color(100, 150, 200));
        this.cancelButton.setBackground(new Color(200, 100, 100));
        this.submitButton.setForeground(Color.WHITE);
        this.cancelButton.setForeground(Color.WHITE);
    }

    /**
     * Sets up the layout and UI components of the panel using GridBagLayout.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // title
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        // amount
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getAmount()), constraints);
        constraints.gridx = 1;
        add(this.amountField, constraints);

        // start date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getStartDate()), constraints);
        constraints.gridx = 1;
        add(this.startDateField, constraints);

        // end date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getEndDate()), constraints);
        constraints.gridx = 1;
        add(this.endDateField, constraints);

        // description
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDescription()), constraints);
        constraints.gridx = 1;
        add(this.descriptionField, constraints);

        // period drop down
        constraints.gridx = 0;
        constraints.gridy++;
        add (new JLabel(viewModel.getPeriod()), constraints);
        constraints.gridx = 1;
        add(this.periodComb, constraints);

        // submit button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(submitButton, constraints);

        // cancel button
        constraints.gridy++;
        add(cancelButton, constraints);
    }

    /**
     * Sets up the action listeners for the buttons and fields.
     */
    private void setupListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(submitButton)) {
                    String period = (String) periodComb.getSelectedItem();
                    if ("custom".equals(period)) {
                        period = viewModel.getState().getTransactionPeriod();
                    }
                    periodicTransactionController.execute(
                            amountField.getText(),
                            startDateField.getText(),
                            descriptionField.getText(),
                            period,
                            endDateField.getText(),
                            "period"
                    );
                }
            }
        });

        cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("Transaction");
        });

        this.amountField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                PeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionAmount(amountField.getText() + evt.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.startDateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                PeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionStartDate(startDateField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.endDateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                PeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionEndDate(endDateField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.descriptionField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                PeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionDescription(descriptionField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });


        this.periodComb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent  evt) {
                PeriodicTransactionState currentState = viewModel.getState();

                // Check if the selected item is "custom"
                if ("custom".equals(periodComb.getSelectedItem())) {
                    // Prompt the user to enter the number of custom days
                    String input = JOptionPane.showInputDialog(
                            null, "Enter the number of custom days:",
                            "Custom Period", JOptionPane.PLAIN_MESSAGE);

                    // set the custom period
                    currentState.setTransactionPeriod(input);
                    viewModel.setState(currentState);
                } else {
                    //set the selected period
                    currentState.setTransactionPeriod((String) periodComb.getSelectedItem());
                    viewModel.setState(currentState);
                }
            }
        });
    }

    /**
     * Clears all text fields in the panel.
     */
    public void clearFields() {
        amountField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        descriptionField.setText("");
    }
}
