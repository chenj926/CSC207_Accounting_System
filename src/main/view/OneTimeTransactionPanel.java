package view;

import interface_adaptors.*;

public class OneTimeTransactionPanel {

    public OneTimeTransactionPanel(OneTimeTransactionViewModel oneTimeTransactionViewModel,
                                   OneTimeTransactionController oneTimeTransactionController,
                                   ViewManagerModel viewManager) {

    }

//    @Override
//    protected void handleRecordButtonClick(TransactionViewModel viewModel) {
//        OneTimeTransactionViewModel oneTimeViewModel = (OneTimeTransactionViewModel) viewModel;
//        oneTimeViewModel.getState().setTransactionAmount(Float.parseFloat(amountField.getText()));
//        oneTimeViewModel.getState().setTransactionDate(dateField.getText());
//        oneTimeViewModel.getState().setTransactionDescription(descriptionField.getText());
//
//        // move logic to use case
//        if (oneTimeViewModel.validateOneTimeTransaction()) {
//            messageLabel.setText("Transaction recorded successfully!");
//        } else {
//            messageLabel.setText(oneTimeViewModel.getState().getError());
//        }
//    }
//
//    @Override
//    protected void updateMessage(TransactionViewModel viewModel) {
//        OneTimeTransactionViewModel oneTimeViewModel = (OneTimeTransactionViewModel) viewModel;
//        if (oneTimeViewModel.validateOneTimeTransaction()) {
//            messageLabel.setText("Transaction recorded successfully!");
//        } else {
//            messageLabel.setText(oneTimeViewModel.getState().getError());
//        }
//    }
}
