package view;

import interface_adaptors.*;

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
    private JLabel messageLabel;

    public TransactionView(TransactionViewModel transactionViewModel, TransactionController transactionController) {
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
            dynamicPanel.add(new OneTimeTransactionPanel((OneTimeTransactionViewModel) currentViewModel));
        } else if (currentViewModel instanceof PeriodicTransactionViewModel) {
            dynamicPanel.add(new PeriodicTransactionPanel((PeriodicTransactionViewModel) currentViewModel));
        }

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
        this.pack();
    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            TransactionViewModel transactionViewModel = new TransactionViewModel("Transaction System");
//            new TransactionView(transactionViewModel).setVisible(true);
//        });
//    }
}

