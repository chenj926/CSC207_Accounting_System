package app;

import data_access.*;
import entity.*;
import interface_adaptors.*;
import use_case.*;
import view.*;

import javax.swing.*;
import java.io.IOException;

public class HomePageUseCaseFactory {

    private HomePageUseCaseFactory() {}

    public static HomePageView create(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel){
        HomePageView homePageView = new HomePageView(homePageViewModel, viewManagerModel);

        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("log in"));
        homePageView.addPropertyChangeListener(evt -> viewManagerModel.changeView("sign in"));

        return homePageView;

    }
}
