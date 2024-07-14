package use_case;

import java.util.*;

import data_access.ShareAccountDataAccessInterface;
import entity.UserAccount;
import entity.AccountFactory;

public class ShareAccountInteractor implements ShareAccountInputBoundary{
    final AccountFactory accountFactory;
    final ShareAccountOutputBoundary presenter;
    final ShareAccountDataAccessInterface usersDataAccessObject;

    // Constructor
    public ShareAccountInteractor(ShareAccountDataAccessInterface shareAccountDataAccessInterface,
                                  ShareAccountOutputBoundary shareAccountOutputBoundary,
                                  AccountFactory accountFactory){
        this.accountFactory = accountFactory;
        this.usersDataAccessObject = shareAccountDataAccessInterface;
        this.presenter = shareAccountOutputBoundary;
    }

    @Override
    public void excute(ShareAccountInputData shareAccountInputData) {
        boolean allIdExist = true;
        // if all the entered share account id exist
        for (String id : shareAccountInputData.getSharedUserIdentifications()){
            if (!usersDataAccessObject.existById(id)){
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
            UserAccount shareAccount = accountFactory.createSharedAccount()

        }

    }
}