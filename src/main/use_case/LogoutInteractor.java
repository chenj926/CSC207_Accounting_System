package use_case;

import entity.UserAccount;
import data_access.LogoutDataAccessInterface;


public class LogoutInteractor implements LogoutInputBoundary{
    final LogoutDataAccessInterface userDataAccessObject;
    final LogoutOutputBoundary presenter;

    public LogoutInteractor(LogoutDataAccessInterface userDataAccessObject, LogoutOutputBoundary presenter) {
        // this.accountFactory = accountFactory;
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        //Assume user already login

        UserAccount userAccount = userDataAccessObject.getById(logoutInputData.getLogout());
        userDataAccessObject.logout(userAccount);
        // prepare output to presenter
        LogoutOutputData logoutOutputData = new LogoutOutputData(logoutInputData.getLogout());
        presenter.prepareSuccessView(logoutOutputData);


    }
}