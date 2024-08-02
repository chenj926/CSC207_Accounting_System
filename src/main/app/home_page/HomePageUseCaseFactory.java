package app.home_page;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.HomePageViewModel;
import view.home_page.HomePageView;

public class HomePageUseCaseFactory {

    private HomePageUseCaseFactory() {}

    public static HomePageView create(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel) {
        HomePageView homePageView = new HomePageView(homePageViewModel, viewManagerModel);

        // Listen for property changes to handle view transitions
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("log in"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("sign up"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("shared account log in"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("shared account sign up"));

        return homePageView;
    }
}
