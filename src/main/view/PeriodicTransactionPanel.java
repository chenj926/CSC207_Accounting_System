package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PeriodicTransactionPanel extends JPanel {
    private final PeriodicTransactionViewModel viewModel;
    private final PeriodicTransactionController periodicTransactionController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JTextField amountField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField descriptionField;
    private JTextField periodField;
    private JButton submitButton;
    private JButton cancelButton;

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

    private void initializeComponents() {
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.amountField = new JTextField(20);
        this.startDateField = new JTextField(20);
        this.endDateField = new JTextField(20);
        this.descriptionField = new JTextField(20);
        this.periodField = new JTextField(20);

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

        // period
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getPeriod()), constraints);
        constraints.gridx = 1;
        add(this.periodField, constraints);

        // submit button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(submitButton, constraints);

        // cancel button
        constraints.gridy++;
        add(cancelButton, constraints);
    }

    private void setupListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(submitButton)) {
                    periodicTransactionController.execute(
                            amountField.getText(),
                            startDateField.getText(),
                            descriptionField.getText(),
                            periodField.getText(),
                            endDateField.getText()
                    );

                    System.out.println("periodic transaction controller: " + periodicTransactionController.toString());
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
                System.out.println("amount: " + currentState.getTransactionAmount());
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
                System.out.println("start date: " + currentState.getTransactionStartDate());
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
                System.out.println("end date: " + currentState.getTransactionEndDate());
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
                System.out.println("description: " + currentState.getTransactionDescription());
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.periodField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                PeriodicTransactionState currentState = viewModel.getState();
                currentState.setTransactionPeriod(periodField.getText() + evt.getKeyChar());
                viewModel.setState(currentState);
                System.out.println("period: " + currentState.getTransactionPeriod());
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public void clearFields() {
        amountField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        descriptionField.setText("");
        periodField.setText("");
    }



//    private JTextField periodField;
//
//    public PeriodicTransactionPanel(PeriodicTransactionViewModel viewModel) {
//        super(viewModel);
//        initializeAdditionalComponents(viewModel);
//        setupLayout(viewModel);
//    }
//
//    private void initializeAdditionalComponents(PeriodicTransactionViewModel viewModel) {
//        periodField = new JTextField(10);
//    }
//
//    @Override
//    protected void setupLayout(TransactionViewModel viewModel) {
//        super.setupLayout(viewModel);
//        PeriodicTransactionViewModel periodicViewModel = (PeriodicTransactionViewModel) viewModel;
//        add(new JLabel(periodicViewModel.getRecurrencePeriodLabel()));
//        add(periodField);
//    }
//
//    @Override
//    protected void handleRecordButtonClick(TransactionViewModel viewModel) {
//        PeriodicTransactionViewModel periodicViewModel = (PeriodicTransactionViewModel) viewModel;
//        periodicViewModel.getState().setTransactionAmount(Float.parseFloat(amountField.getText()));
//        periodicViewModel.getState().setTransactionEndDate(dateField.getText());
//        periodicViewModel.getState().setTransactionDescription(descriptionField.getText());
//        periodicViewModel.getState().setTransactionPeriod(Integer.parseInt(periodField.getText()));
//
//        if (periodicViewModel.validatePeriodicTransaction()) {
//            messageLabel.setText("Transaction recorded successfully!");
//        } else {
//            messageLabel.setText(periodicViewModel.getState().getError());
//        }
//    }
//
//    @Override
//    protected void updateMessage(TransactionViewModel viewModel) {
//        PeriodicTransactionViewModel periodicViewModel = (PeriodicTransactionViewModel) viewModel;
//        if (periodicViewModel.validatePeriodicTransaction()) {
//            messageLabel.setText("Transaction recorded successfully!");
//        } else {
//            messageLabel.setText(periodicViewModel.getState().getError());
//        }
//    }
}
