package use_case;

import entity.UserAccount;
// import entity.AccountFactory;
import data_access.LogoutDataAccessInterface;


public class LogoutInteractor implements LogoutInputBoundary{
    // final AccountFactory accountFactory;
    // final LogoutDataAccessInterface userDataAccessObject;
    final LogoutOutputBoundary presenter;

    public LogoutInteractor(LogoutOutputBoundary presenter) {
        // this.accountFactory = accountFactory;
        this.presenter = presenter;
       // this.userDataAccessObject = LogoutDataAccessInterface;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        //Assume user already login
        // prepare output to presenter
        LogoutOutputData logoutOutputData = new LogoutOutputData(logoutInputData.getLogout());
        presenter.prepareSuccessView(logoutOutputData);


    }
}
