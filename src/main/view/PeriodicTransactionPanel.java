package view;

import interface_adaptors.*;

import javax.swing.*;

public class PeriodicTransactionPanel{
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
