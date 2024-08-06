package use_case.homepage;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;

public class HomepageTwoInteractor implements HomepageTwoInputBoundary{
    private final UserAccountDataAccessInterface userDataAccessObject;
    private final HomepageTwoOutputBoundary presenter;

    public HomepageTwoInteractor(UserAccountDataAccessInterface userDataAccessObject,
                                 HomepageTwoOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    public void execute(HomepageTwoInputData inputData) {
        String id = inputData.getId();
        UserAccount user = userDataAccessObject.getById(id);
        String username = user.getUsername();
        String totalIncome = String.valueOf(user.getTotalIncome());
        String totalOutflow = String.valueOf(user.getTotalOutflow());
        String totalBalance = String.valueOf(user.getTotalBalance());

        String[] basicUserInfo = {username, totalIncome, totalOutflow, totalBalance};
        HomepageTwoOutputData outputData = new HomepageTwoOutputData(basicUserInfo);
        presenter.prepareSuccessView(outputData);
    }
}
