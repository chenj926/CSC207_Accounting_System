package use_case;

import java.util.*;

import data_access.ShareAccountDataAccessInterface;
import entity.SharedAccount;
import entity.AccountFactory;

public class ShareAccountInteractor implements ShareAccountInputBoundary{
    final AccountFactory accountFactory;
    final ShareAccountOutputBoundary presenter;
    final ShareAccountDataAccessInterface shareAccountDataAccessObject;

    // Constructor
    public ShareAccountInteractor(ShareAccountDataAccessInterface shareAccountDataAccessInterface,
                                  ShareAccountOutputBoundary shareAccountOutputBoundary,
                                  AccountFactory accountFactory){
        this.accountFactory = accountFactory;
        this.shareAccountDataAccessObject = shareAccountDataAccessInterface;
        this.presenter = shareAccountOutputBoundary;
    }

    @Override
    public void execute(ShareAccountInputData shareAccountInputData) {
        boolean allIdExist = true;
        // if all the entered share account id exist
        for (String id : shareAccountInputData.getSharedUserIdentifications()){
            if (!shareAccountDataAccessObject.existById(id)){
                allIdExist = false;
                break;
            }
        }

        // if some id entered not exist
        if (!allIdExist) {
            presenter.prepareFailView("Some entered share account identification is incorrect," +
                    " please enter again!!");
        }
        // now all id exist
        else {
            // create new share acc
            SharedAccount shareAccount =
                    accountFactory.createSharedAccount(shareAccountInputData.getShareAccountIdentification());
            shareAccountDataAccessObject.save(shareAccount);  // save this user

            // prepare output to presenter
            ShareAccountOutputData shareAccountOutputData = new ShareAccountOutputData(shareAccount.getIdentification(),
                    shareAccount.getSharedUserIdentifications(), false);
            presenter.prepareSuccessView(shareAccountOutputData);
        }
    }
}