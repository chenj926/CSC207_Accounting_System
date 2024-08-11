package data_access;

import data_access.account.shared_account.CSVSharedAccountDataAccessObject;
import data_access.account.shared_account.InMemorySharedAccountDataAccessObject;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.account.user_account.CSVUserAccountDataAccessObject;
import data_access.account.user_account.InMemoryUserAccountDataAccessObject;
import data_access.account.user_account.UserAccountDataAccessInterface;
import data_access.authentication.shared_account.CSVSharedAccountLoginDataAccessObject;
import data_access.authentication.shared_account.InMemorySharedAccountLoginDataAccessObject;
import data_access.authentication.shared_account.SharedAccountLoginDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import data_access.authentication.user_account.CSVUserLoginDataAccessObject;
import data_access.authentication.user_account.InMemoryLoginoutDataAccessObject;
import data_access.authentication.user_account.UserAccountLoginDataAccessInterface;
import data_access.authentication.user_account.UserSignupDataAccessInterface;
import data_access.transaction.*;

/**
 * Factory class to provide access to different types of data access objects (DAOs).
 * <p>
 * This class supports both in-memory and CSV-based implementations of DAOs. It is responsible for creating
 * and returning the appropriate DAO instance based on the specified configuration.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class DAOFactory {
    private static CSVUserAccountDataAccessObject csvUserAccountDAO;
    private static CSVSharedAccountDataAccessObject csvSharedAccountDAO;

    private static CSVUserLoginDataAccessObject csvUserLoginDAO;
    private static CSVSharedAccountLoginDataAccessObject csvSharedAccountUserLoginDAO;

    private static InMemoryUserAccountDataAccessObject inMemoryUserAccountDAO;
    private static InMemorySharedAccountDataAccessObject inMemorySharedAccountDAO;

    private static InMemoryLoginoutDataAccessObject inMemoryLoginoutDAO;
    private static InMemorySharedAccountLoginDataAccessObject inMemorySharedAccountLoginDataAccessObject;

    private static InMemoryOneTimeDataAccessObject inMemoryOneTimeDataAccessObject;
    private static InMemoryPeriodicDataAccessObject inMemoryPeriodicDataAccessObject;

    private static boolean useInMemory = false; // Flag to determine which DAO to use

    /**
     * Sets the flag to determine whether to use in-memory DAOs or CSV-based DAOs.
     *
     * @param useInMemory {@code true} to use in-memory DAOs; {@code false} to use CSV-based DAOs
     */
    public static void setUseInMemory(boolean useInMemory) {
        DAOFactory.useInMemory = useInMemory;
    }

    /**
     * Returns a singleton instance of {@link UserSignupDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryUserAccountDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserSignupDataAccessInterface} instance
     */
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

    public static synchronized SharedAccountSignupDataAccessInterface getSharedAccountSignupDataAccessObject() {
        if (useInMemory) {
            if (inMemorySharedAccountDAO == null) {
                inMemorySharedAccountDAO = new InMemorySharedAccountDataAccessObject();
            }
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link SharedAccountDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemorySharedAccountDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVSharedAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link SharedAccountDataAccessInterface} instance
     */
    public static synchronized SharedAccountDataAccessInterface getShareAccountDataAccessObject() {
        if (useInMemory) {
            if (inMemorySharedAccountDAO == null) {
                inMemorySharedAccountDAO = new InMemorySharedAccountDataAccessObject();
            }
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }

    public static synchronized UserAccountDataAccessInterface getUserAccountDataAccessObject() {
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

    /**
     * Returns a singleton instance of {@link UserAccountLoginDataAccessInterface}.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryLoginoutDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserLoginDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountLoginDataAccessInterface} instance
     */
    public static synchronized UserAccountLoginDataAccessInterface getLoginDataAccessObject() {
        if (useInMemory) {
            if (inMemoryLoginoutDAO == null) {
                inMemoryLoginoutDAO = new InMemoryLoginoutDataAccessObject();
            }
            return inMemoryLoginoutDAO;
        } else {
            if (csvUserLoginDAO == null) {
                csvUserLoginDAO = new CSVUserLoginDataAccessObject();
            }
            return csvUserLoginDAO;
        }
    }

    public static synchronized SharedAccountLoginDataAccessInterface getSharedAccountLoginDataAccessObject() {
        if (useInMemory) {
            if (inMemorySharedAccountLoginDataAccessObject == null) {
                inMemorySharedAccountLoginDataAccessObject = new InMemorySharedAccountLoginDataAccessObject();
            }
            return inMemorySharedAccountLoginDataAccessObject;
        } else {
            if (csvSharedAccountUserLoginDAO == null) {
                csvSharedAccountUserLoginDAO = new CSVSharedAccountLoginDataAccessObject();
            }
            return csvSharedAccountUserLoginDAO;
        }
    }

    /**
     * Returns a singleton instance of {@link UserAccountDataAccessInterface} for one-time transactions.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryOneTimeDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountDataAccessInterface} instance
     */
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

    /**
     * Returns a singleton instance of {@link UserAccountDataAccessInterface} for periodic transactions.
     * <p>
     * If {@code useInMemory} is {@code true}, returns an instance of {@link InMemoryPeriodicDataAccessObject}.
     * Otherwise, returns an instance of {@link CSVUserAccountDataAccessObject}.
     * </p>
     *
     * @return the {@link UserAccountDataAccessInterface} instance
     */
    public static synchronized UserAccountDataAccessInterface getPeriodicTransactionDAO() {
        if (useInMemory) {
            if (inMemoryPeriodicDataAccessObject == null) {
                inMemoryPeriodicDataAccessObject = new InMemoryPeriodicDataAccessObject();
            }
            return inMemoryPeriodicDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    public static synchronized UserAccountDataAccessInterface getFinancialReportDAO() {
        if (useInMemory) {
//            if (inMemoryPeriodicDataAccessObject == null) {
//                inMemoryPeriodicDataAccessObject = new InMemoryPeriodicDataAccessObject();
//            }
            return inMemoryPeriodicDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    public static synchronized SharedAccountDataAccessInterface getSharedAccountFinancialReportDAO() {
        if (useInMemory) {
//            if (inMemoryPeriodicDataAccessObject == null) {
//                inMemoryPeriodicDataAccessObject = new InMemoryPeriodicDataAccessObject();
//            }
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }

    public static synchronized UserAccountDataAccessInterface getHomepageTwoDAO() {
        if (useInMemory) {
//            if (inMemoryPeriodicDataAccessObject == null) {
//                inMemoryPeriodicDataAccessObject = new InMemoryPeriodicDataAccessObject();
//            }
            return inMemoryPeriodicDataAccessObject;
        } else {
            if (csvUserAccountDAO == null) {
                csvUserAccountDAO = new CSVUserAccountDataAccessObject();
            }
            return csvUserAccountDAO;
        }
    }

    public static synchronized SharedAccountDataAccessInterface getSharedAccountHomepageTwoDAO() {
        if (useInMemory) {
//            if (inMemoryPeriodicDataAccessObject == null) {
//                inMemoryPeriodicDataAccessObject = new InMemoryPeriodicDataAccessObject();
//            }
            return inMemorySharedAccountDAO;
        } else {
            if (csvSharedAccountDAO == null) {
                csvSharedAccountDAO = new CSVSharedAccountDataAccessObject();
            }
            return csvSharedAccountDAO;
        }
    }
}
