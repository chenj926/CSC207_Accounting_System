package data_access;

import interface_adaptors.OneTimeTransactionPresenter;

public class DAOFactory {
    private static CSVUserAccountDataAccessObject csvUserAccountDAO;
    private static CSVSharedAccountDataAccessObject csvSharedAccountDAO;
//    private static CSVUserLoginoutDataAccessObject csvUserLoginoutDAO;

    private static InMemoryUserAccountDataAccessObject inMemoryUserAccountDAO;
    private static InMemoryShareAccountDataAccessObject inMemorySharedAccountDAO;
    private static InMemoryLoginoutDataAccessObject inMemoryLoginoutDAO;
    private static InMemoryOneTimeDataAccessObject inMemoryOneTimeDataAccessObject;
    private static InMemoryPeriodicDataAccessObject inMemoryPeriodicDataAccessObject;

    private static boolean useInMemory = false; // Flag to determine which DAO to use

    public static void setUseInMemory(boolean useInMemory) {
        DAOFactory.useInMemory = useInMemory;
    }

    // synchronized such that only 2 DAO is operating at once
    public static synchronized UserSignupDataAccessInterface getUserSignupDataAccessObject() {
        if (useInMemory) {
            if (inMemoryUserAccountDAO == null) {
                inMemoryUserAccountDAO = new InMemoryUserAccountDataAccessObject();
            }
            return inMemoryUserAccountDAO;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    public static synchronized ShareAccountDataAccessInterface getShareAccountDataAccessObject() {
        if (useInMemory) {
            if (inMemorySharedAccountDAO == null) {
                inMemorySharedAccountDAO = new InMemoryShareAccountDataAccessObject();
            }
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }

    public static synchronized LoginDataAccessInterface getLoginDataAccessObject() {
        if (useInMemory) {
            if (inMemoryLoginoutDAO == null) {
                inMemoryLoginoutDAO = new InMemoryLoginoutDataAccessObject();
            }
            return inMemoryLoginoutDAO;
        } else {
            if (csvUserLoginoutDAO == null) {
                csvUserLoginoutDAO = new CSVUserLoginoutDataAccessObject();
            }
            return csvUserLoginoutDAO;
        }
    }

    public static synchronized LogoutDataAccessInterface getLogoutDataAccessObject() {
        // 先不管
        if (useInMemory) {
            if (inMemoryLoginoutDAO == null) {
                inMemoryLoginoutDAO = new InMemoryLoginoutDataAccessObject();
            }
            return inMemoryLoginoutDAO;
        } else {
            if (csvUserLoginoutDAO == null) {
                csvUserLoginoutDAO = new CSVUserLoginoutDataAccessObject();
            }
            return csvUserLoginoutDAO;
        }
    }

    public static synchronized UserAccountDataAccessInterface getOneTimeTransactionDAO() {
        if (useInMemory) {
            if (inMemoryOneTimeDataAccessObject == null) {
                inMemoryOneTimeDataAccessObject = new InMemoryOneTimeDataAccessObject();
            }
            return inMemoryOneTimeDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    public static synchronized UserAccountDataAccessInterface getPeriodicTransactionDAO() {
        if (useInMemory) {
            if (inMemoryPeriodicDataAccessObject == null) {
                inMemoryPeriodicDataAccessObject = new InMemoryPeriodicDataAccessObject();
            }
            return inMemoryPeriodicDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserLoginoutDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }
}
