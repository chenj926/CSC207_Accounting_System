package view.Transaction;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The TransactionView class represents the main view for managing transactions. It provides a user interface
 * to interact with different types of transactions, such as one-time and periodic transactions.
 * <p>
 * This class extends JFrame and implements PropertyChangeListener to respond to changes in the view model's
 * state.
 * </p>
 *
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class TransactionView extends JFrame implements PropertyChangeListener {

    private final TransactionViewModel viewModel;
    private final TransactionPanel transactionPanel;

    /**
     * Constructs a TransactionView with the specified view model and view manager.
     *
     * @param transactionViewModel the view model for the transaction view
     * @param viewManager           the view manager for handling view transitions
     */
    public TransactionView(TransactionViewModel transactionViewModel, ViewManagerModel viewManager) {
        super("Transaction View");
        this.viewModel = transactionViewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.transactionPanel = new TransactionPanel(viewModel, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the transaction panel to the content pane.
     */
    private void setupUI() {
        this.getContentPane().add(transactionPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events. Currently, this method does not perform any actions but can be
     * customized to handle changes in the view model's properties.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here if needed
    }

//    private void initializeComponents() {
//        mainPanel = new JPanel(new BorderLayout());
//        dynamicPanel = new JPanel();
//        oneTimeButton = new JButton(transactionViewModel.getOneTimeButtonLabel());
//        periodicButton = new JButton(transactionViewModel.getPeriodicButtonLabel());
//        messageLabel = new JLabel();
//    }
//
//    private void setupLayout() {
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(oneTimeButton);
//        buttonPanel.add(periodicButton);
//
//        mainPanel.add(buttonPanel, BorderLayout.NORTH);
//        mainPanel.add(dynamicPanel, BorderLayout.CENTER);
//        mainPanel.add(messageLabel, BorderLayout.SOUTH);
//
//        updateDynamicPanel();
//
//        this.setContentPane(mainPanel);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.pack();
//        this.setVisible(true);
//    }
//
//    private void setupListeners() {
//        oneTimeButton.addActionListener(e -> transactionViewModel.selectOneTimeTransaction());
//        periodicButton.addActionListener(e -> transactionViewModel.selectPeriodicTransaction());
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        if ("currentViewModel".equals(evt.getPropertyName())) {
////            updateDynamicPanel();
//        }
//    }

//    private void updateDynamicPanel() {
//        dynamicPanel.removeAll();
//        TransactionViewModel currentViewModel = transactionViewModel.getCurrentViewModel();
//
//        if (currentViewModel == null) {
//            JPanel buttonPanel = new JPanel();
//            buttonPanel.add(oneTimeButton);
//            buttonPanel.add(periodicButton);
//            dynamicPanel.add(buttonPanel);
//        } else if (currentViewModel instanceof OneTimeTransactionViewModel) {
//            dynamicPanel.add(new OneTimeTransactionPanel((OneTimeTransactionViewModel) currentViewModel));
//        } else if (currentViewModel instanceof PeriodicTransactionViewModel) {
//            dynamicPanel.add(new PeriodicTransactionPanel((PeriodicTransactionViewModel) currentViewModel));
//        }
//
//        dynamicPanel.revalidate();
//        dynamicPanel.repaint();
//        this.pack();
//    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            TransactionViewModel transactionViewModel = new TransactionViewModel("Transaction System");
//            new TransactionView(transactionViewModel).setVisible(true);
//        });
//    }
}

