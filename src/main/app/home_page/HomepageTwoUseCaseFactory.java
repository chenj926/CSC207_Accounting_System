package app.home_page;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoViewModel;
import interface_adaptors.transaction.TransactionViewModel;
import view.home_page.HomepageTwoView;
import view.transaction.TransactionView;

public class HomepageTwoUseCaseFactory {
    private HomepageTwoUseCaseFactory() {

    }

    public static HomepageTwoView create(ViewManagerModel viewManagerModel, HomepageTwoViewModel homepageTwoViewModel) {
        HomepageTwoView homepageTwoView = new HomepageTwoView(homepageTwoViewModel, viewManagerModel);

        homepageTwoView.addPropertyChangeListener(evt -> viewManagerModel.changeView("One Time Transaction"));
        homepageTwoView.addPropertyChangeListener(evt -> viewManagerModel.changeView("Periodic Transaction"));
        homepageTwoView.addPropertyChangeListener(evt -> viewManagerModel.changeView("Homepage Two"));


        return homepageTwoView;
    }

}
