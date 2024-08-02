package view.transaction;

import interface_adaptors.*;
import interface_adaptors.transaction.TransactionViewModel;

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
}

