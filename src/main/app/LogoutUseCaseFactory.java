package app;

import data_access.*;
import entity.*;
import interface_adaptors.*;
import use_case.*;
import view.*;

import javax.swing.*;
import java.io.IOException;

public class LogoutUseCaseFactory {

    private LogoutUseCaseFactory() {}

    public static LogoutView create(ViewManagerModel viewManagerModel, LogoutViewModel logoutViewModel) {
        LogoutController logoutController = createLogoutUseCase(viewManagerModel, logoutViewModel);
        return new LogoutView(logoutViewModel, logoutController);
    }

    private static LogoutController createLogoutUseCase(ViewManagerModel viewManagerModel, LogoutViewModel logoutViewModel) {

        LogoutDataAccessInterface dataAccessObject = DAOFactory.getLogoutDataAccessObject();
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(logoutViewModel, viewManagerModel);
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(dataAccessObject, logoutOutputBoundary);

        return new LogoutController(logoutInteractor);
    }
}
