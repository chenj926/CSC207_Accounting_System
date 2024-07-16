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
        oneTimeButton = new JButton(transactionViewModel.ONE_TIME_BUTTON_LABEL);
        periodicButton = new JButton(transactionViewModel.PERIODIC_BUTTON_LABEL);
    }

    private void setupLayout() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(oneTimeButton);
        buttonPanel.add(periodicButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(dynamicPanel, BorderLayout.CENTER);

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

        if (currentViewModel instanceof OneTimeTransactionViewModel) {
            dynamicPanel.add(createOneTimeTransactionPanel((OneTimeTransactionViewModel) currentViewModel));
        } else if (currentViewModel instanceof PeriodicTransactionViewModel) {
            dynamicPanel.add(createPeriodicTransactionPanel((PeriodicTransactionViewModel) currentViewModel));
        }

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
        this.pack();
    }

    private JPanel createOneTimeTransactionPanel(OneTimeTransactionViewModel viewModel) {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new LabelTextPanel(new JLabel(viewModel.AMOUNT_LABEL), new JTextField(10)));
        panel.add(new LabelTextPanel(new JLabel(viewModel.DATE_LABEL), new JTextField(10)));
        panel.add(new LabelTextPanel(new JLabel(viewModel.DESCRIPTION_LABEL), new JTextField(10)));
        panel.add(new JButton(viewModel.RECORD_BUTTON_LABEL));
        panel.add(new JButton(viewModel.CANCEL_BUTTON_LABEL));
        return panel;
    }

    private JPanel createPeriodicTransactionPanel(PeriodicTransactionViewModel viewModel) {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new LabelTextPanel(new JLabel(viewModel.TRANSACTION_AMOUNT_LABEL), new JTextField(10)));
        panel.add(new LabelTextPanel(new JLabel(viewModel.TRANSACTION_DATE_LABEL), new JTextField(10)));
        panel.add(new LabelTextPanel(new JLabel(viewModel.TRANSACTION_DESCRIPTION_LABEL), new JTextField(10)));
        panel.add(new LabelTextPanel(new JLabel(viewModel.RECURRENCE_LABEL), new JTextField(10)));
        panel.add(new JButton("Record"));
        panel.add(new JButton("Cancel"));
        return panel;
    }

    public static void main(String[] args) {
        TransactionViewModel viewModel = new TransactionViewModel("Transaction View");
        new TransactionView(viewModel);
    }
}

