package use_case.share_account;

import data_access.account.ShareAccountDataAccessInterface;
import entity.account.SharedAccount;
import entity.account.AccountFactory;

/**
 * The ShareAccountInteractor class implements the ShareAccountInputBoundary interface.
 * It handles the process of sharing an account by validating the input data, interacting with the data access layer,
 * and using the presenter to prepare the output views.
 *
 * @author Eric
 */
public class ShareAccountInteractor implements ShareAccountInputBoundary {
    final AccountFactory accountFactory;
    final ShareAccountOutputBoundary presenter;
    final ShareAccountDataAccessInterface shareAccountDataAccessObject;

    /**
     * Constructs a ShareAccountInteractor object with the specified data access interface, output boundary, and account factory.
     *
     * @param shareAccountDataAccessInterface the data access interface for shared account data
     * @param shareAccountOutputBoundary      the output boundary for presenting the share account results
     * @param accountFactory                  the factory for creating shared accounts
     */
    public ShareAccountInteractor(ShareAccountDataAccessInterface shareAccountDataAccessInterface,
                                  ShareAccountOutputBoundary shareAccountOutputBoundary,
                                  AccountFactory accountFactory){
        this.accountFactory = accountFactory;
        this.shareAccountDataAccessObject = shareAccountDataAccessInterface;
        this.presenter = shareAccountOutputBoundary;
    }

    /**
     * Executes the share account process with the given input data.
     *
     * @param shareAccountInputData the input data required for the share account process
     */
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