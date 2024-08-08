package app.transaction;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.SharedAccountTransactionViewModel;
import view.transaction.SharedAccountTransactionView;

/**
 * Factory class for creating the view related to shared account transactions.
 * <p>
 * This factory is responsible for assembling the view components and managing the transitions
 * between different views related to shared account transactions, such as one-time and periodic transactions.
 * </p>
 * @author Zella
 */
public class SharedAccountTransactionUseCaseFactory {

    private SharedAccountTransactionUseCaseFactory() {}

    /**
     * Creates and returns the view for shared account transactions.
     * <p>
     * This method sets up property change listeners to handle transitions between different
     * shared account transaction views based on the user's actions, such as initiating a one-time
     * transaction or a periodic transaction.
     * </p>
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param sharedAccountTransactionViewModel the view model that holds the state of the shared account transaction
     * @return the assembled SharedAccountTransactionView
     */
    public static SharedAccountTransactionView createTransactionView(ViewManagerModel viewManagerModel, SharedAccountTransactionViewModel sharedAccountTransactionViewModel) {
        SharedAccountTransactionView sharedAccountTransactionView = new SharedAccountTransactionView(sharedAccountTransactionViewModel, viewManagerModel);

        // Setup property change listeners to handle view transitions
        sharedAccountTransactionView.addPropertyChangeListener(evt -> {
            switch (evt.getPropertyName()) {
                case "One Time Transaction":
                    viewManagerModel.changeView("Shared Account One Time Transaction");
                    break;
                case "Periodic Transaction":
                    viewManagerModel.changeView("Shared Account Periodic Transaction");
                    break;
                case "Show Related Shared Account":
                    // Add logic to handle showing related shared accounts
                    viewManagerModel.changeView("Show Related Shared Account");
                    break;
            }
        });

        return sharedAccountTransactionView;
    }
}

