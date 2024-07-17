package view;

import interface_adaptors.OneTimeTransactionViewModel;
import interface_adaptors.PeriodicTransactionViewModel;
import interface_adaptors.TransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TransactionView extends JFrame implements PropertyChangeListener {

    private final TransactionViewModel transactionViewModel;
    private JPanel mainPanel;
    private JPanel dynamicPanel;
    private JButton oneTimeButton;
    private JButton periodicButton;
    private JTextField amountField;
    private JTextField dateField;
    private JTextField descriptionField;
    private JLabel messageLabel;

    public TransactionView(TransactionViewModel transactionViewModel) {
        this.transactionViewModel = transactionViewModel;
        transactionViewModel.addPropertyChangeListener(this);
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout());
        dynamicPanel = new JPanel();
        oneTimeButton = new JButton(transactionViewModel.getOneTimeButtonLabel());
        periodicButton = new JButton(transactionViewModel.getPeriodicButtonLabel());
        messageLabel = new JLabel();
    }

    private void setupLayout() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(oneTimeButton);
        buttonPanel.add(periodicButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(dynamicPanel, BorderLayout.CENTER);
        mainPanel.add(messageLabel, BorderLayout.SOUTH);

        updateDynamicPanel();

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void setupListeners() {
        oneTimeButton.addActionListener(e -> transactionViewModel.selectOneTimeTransaction());
        periodicButton.addActionListener(e -> transactionViewModel.selectPeriodicTransaction());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("currentViewModel".equals(evt.getPropertyName())) {
            updateDynamicPanel();
        }
    }

    private void updateDynamicPanel() {
        dynamicPanel.removeAll();
        TransactionViewModel currentViewModel = transactionViewModel.getCurrentViewModel();

        if (currentViewModel == null) {
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(oneTimeButton);
            buttonPanel.add(periodicButton);
            dynamicPanel.add(buttonPanel);
        } else if (currentViewModel instanceof OneTimeTransactionViewModel) {
            dynamicPanel.add(createOneTimeTransactionPanel((OneTimeTransactionViewModel) currentViewModel));
        } else if (currentViewModel instanceof PeriodicTransactionViewModel) {
            dynamicPanel.add(createPeriodicTransactionPanel((PeriodicTransactionViewModel) currentViewModel));
        }

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
        this.pack();
    }

    private JPanel createOneTimeTransactionPanel(OneTimeTransactionViewModel viewModel) {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        amountField = new JTextField(10);
        dateField = new JTextField(10);
        descriptionField = new JTextField(10);
        JButton recordButton = new JButton(viewModel.getRecordButtonLabel());
        JButton cancelButton = new JButton(viewModel.getCancelButtonLabel());

        panel.add(new LabelTextPanel(new JLabel(viewModel.getAmountLabel()), amountField));
        panel.add(new LabelTextPanel(new JLabel(viewModel.getDateLabel()), dateField));
        panel.add(new LabelTextPanel(new JLabel(viewModel.getDescriptionLabel()), descriptionField));
        panel.add(recordButton);
        panel.add(cancelButton);

        recordButton.addActionListener(e -> handleRecordButtonClick(viewModel));
        cancelButton.addActionListener(e -> transactionViewModel.resetTransactionView());

        return panel;
    }

    private void handleRecordButtonClick(OneTimeTransactionViewModel viewModel) {
        viewModel.getState().setTransactionAmount(Float.parseFloat(amountField.getText()));
        viewModel.getState().setTransactionDate(dateField.getText());
        viewModel.getState().setTransactionDescription(descriptionField.getText());

        if (viewModel.validateOneTimeTransaction()) {
            messageLabel.setText("Transaction recorded successfully!");
        } else {
            messageLabel.setText(viewModel.getState().getError());
        }
    }

    private JPanel createPeriodicTransactionPanel(PeriodicTransactionViewModel viewModel) {
        JPanel panel = new JPanel(new GridLayout(6, 2));
        amountField = new JTextField(10);
        dateField = new JTextField(10);
        descriptionField = new JTextField(10);
        JTextField periodField = new JTextField(10);
        JButton recordButton = new JButton(viewModel.getRecordButtonLabel());
        JButton cancelButton = new JButton(viewModel.getCancelButtonLabel());

        panel.add(new LabelTextPanel(new JLabel(viewModel.getAmountLabel()), amountField));
        panel.add(new LabelTextPanel(new JLabel(viewModel.getDateLabel()), dateField));
        panel.add(new LabelTextPanel(new JLabel(viewModel.getDescriptionLabel()), descriptionField));
        panel.add(new LabelTextPanel(new JLabel(viewModel.getRecurrencePeriodLabel()), periodField));
        panel.add(recordButton);
        panel.add(cancelButton);

        recordButton.addActionListener(e -> handleRecordButtonClick(viewModel));
        cancelButton.addActionListener(e -> transactionViewModel.resetTransactionView());

        return panel;
    }

    private void handleRecordButtonClick(PeriodicTransactionViewModel viewModel) {
        viewModel.getState().setTransactionAmount(Float.parseFloat(amountField.getText()));
        viewModel.getState().setTransactionEndDate(dateField.getText());
        viewModel.getState().setTransactionDescription(descriptionField.getText());

        if (viewModel.validatePeriodicTransaction()) {
            messageLabel.setText("Transaction recorded successfully!");
        } else {
            messageLabel.setText(viewModel.getState().getError());
        }
    }

}

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            TransactionViewModel transactionViewModel = new TransactionViewModel("Transaction System");
//            new TransactionView(transactionViewModel).setVisible(true);
//        });
//    }
//}

