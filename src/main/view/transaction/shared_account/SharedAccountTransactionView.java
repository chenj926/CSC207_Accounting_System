package view.transaction.shared_account;

import interface_adaptors.*;
import interface_adaptors.transaction.SharedAccountTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The SharedAccountTransactionView class represents the main view for managing shared account transactions.
 * It provides a user interface to interact with different types of transactions, such as one-time and periodic transactions,
 * and offers the functionality to add users to a shared account.
 * <p>
 * This class extends JFrame and implements PropertyChangeListener to respond to changes in the view model's state.
 * </p>
 *
 * @author Xile
 * @author Jessica
 * @author Eric
 */
public class SharedAccountTransactionView extends JFrame implements PropertyChangeListener {

    private final SharedAccountTransactionViewModel viewModel;
    private final SharedAccountTransactionPanel sharedAccountTransactionPanel;

    /**
     * Constructs a SharedAccountTransactionView with the specified view model and view manager.
     *
     * @param sharedAccountTransactionViewModel the view model for the shared account transaction view
     * @param viewManager                       the view manager for handling view transitions
     */
    public SharedAccountTransactionView(SharedAccountTransactionViewModel sharedAccountTransactionViewModel, ViewManagerModel viewManager) {
        super("Shared Account Transaction View");
        this.viewModel = sharedAccountTransactionViewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.sharedAccountTransactionPanel = new SharedAccountTransactionPanel(viewModel, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the shared account transaction panel to the content pane.
     */
    private void setupUI() {
        this.getContentPane().add(sharedAccountTransactionPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events. This method can be customized to handle changes in the view model's properties,
     * such as updating UI components based on the model's state.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
