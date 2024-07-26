package app;

import interface_adaptors.*;
import view.home_page.HomePageView;

public class HomePageUseCaseFactory {

    private HomePageUseCaseFactory() {}

    public static HomePageView create(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel){
        HomePageView homePageView = new HomePageView(homePageViewModel, viewManagerModel);

        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("log in"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("sign in"));

        return homePageView;

    }
}
