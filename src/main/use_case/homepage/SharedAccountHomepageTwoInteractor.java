package use_case.homepage;

import data_access.account.SharedAccountDataAccessInterface;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.SharedAccount;
import entity.account.UserAccount;

public class SharedAccountHomepageTwoInteractor extends HomepageTwoInteractor<
        SharedAccountDataAccessInterface,
        SharedAccountHomepageTwoOutputBoundary,
        SharedAccountHomepageTwoInputData,
        SharedAccountHomepageTwoOutputData> implements SharedAccountHomepageTwoInputBoundary{

    public SharedAccountHomepageTwoInteractor(SharedAccountDataAccessInterface userDataAccessObject,
                                              SharedAccountHomepageTwoOutputBoundary presenter) {
        super(userDataAccessObject, presenter);
    }

    @Override
    public void execute(SharedAccountHomepageTwoInputData inputData) {
        String id = inputData.getId();
        SharedAccount user = this.userDataAccessObject.getById(id);
        String userIds = user.getIdentification();
        String totalIncome = String.valueOf(user.getTotalIncome());
        String totalOutflow = String.valueOf(user.getTotalOutflow());
        String totalBalance = String.valueOf(user.getTotalBalance());

        String[] basicUserInfo = {userIds, totalIncome, totalOutflow, totalBalance};
        SharedAccountHomepageTwoOutputData outputData = new SharedAccountHomepageTwoOutputData(basicUserInfo);
        this.presenter.prepareSuccessView(outputData);
    }

}
