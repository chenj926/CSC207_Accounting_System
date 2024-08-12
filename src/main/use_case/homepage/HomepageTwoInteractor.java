package use_case.homepage;

import data_access.account.AccountDataAccessInterface;

public abstract class HomepageTwoInteractor<
        DAO extends AccountDataAccessInterface,
        PRE extends HomepageTwoOutBoundary<O>,
        I extends HomepageTwoInputData,
        O extends HomepageTwoOutputData> implements HomepageTwoInputBoundary<I> {

    protected final DAO userDataAccessObject;
    protected final PRE presenter;

    public HomepageTwoInteractor(DAO userDataAccessObject,
                                            PRE presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public abstract void execute(I inputData);
}
