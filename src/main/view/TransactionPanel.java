package view;

import interface_adaptors.TransactionViewModel;

import javax.swing.*;
import java.awt.*;

public abstract class TransactionPanel extends JPanel {
    protected JTextField amountField;
    protected JTextField dateField;
    protected JTextField descriptionField;
    protected JLabel messageLabel;

    public TransactionPanel(TransactionViewModel viewModel) {
        initializeComponents(viewModel);
        setupLayout(viewModel);
        setupListeners(viewModel);
    }

    private void initializeComponents(TransactionViewModel viewModel) {
        amountField = new JTextField(10);
        dateField = new JTextField(10);
        descriptionField = new JTextField(10);
        messageLabel = new JLabel();
    }

    protected void setupLayout(TransactionViewModel viewModel) {
        setLayout(new GridLayout(5, 2));

        add(new JLabel(viewModel.getAmountLabel()));
        add(amountField);
        add(new JLabel(viewModel.getDateLabel()));
        add(dateField);
        add(new JLabel(viewModel.getDescriptionLabel()));
        add(descriptionField);

        JButton recordButton = new JButton(viewModel.getRecordButtonLabel());
        JButton cancelButton = new JButton(viewModel.getCancelButtonLabel());

        add(recordButton);
        add(cancelButton);
        add(messageLabel);

        recordButton.addActionListener(e -> handleRecordButtonClick(viewModel));
        cancelButton.addActionListener(e -> viewModel.resetTransactionView());
    }

    protected abstract void handleRecordButtonClick(TransactionViewModel viewModel);

    protected void setupListeners(TransactionViewModel viewModel) {
        viewModel.addPropertyChangeListener(evt -> {
            if ("transaction".equals(evt.getPropertyName())) {
                updateMessage(viewModel);
            }
        });
    }

    protected abstract void updateMessage(TransactionViewModel viewModel);
}

