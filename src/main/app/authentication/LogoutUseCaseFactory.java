package app.authentication;

import data_access.*;
import data_access.authentication.LogoutDataAccessInterface;
import interface_adaptors.*;
import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutPresenter;
import interface_adaptors.logout.LogoutViewModel;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import view.logout.LogoutView;

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
