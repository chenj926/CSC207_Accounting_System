package app.transaction;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.SharedAccountTransactionViewModel;
import view.transaction.shared_account.SharedAccountTransactionView;

public class SharedAccountTransactionUseCaseFactory {

    private SharedAccountTransactionUseCaseFactory() {}

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

