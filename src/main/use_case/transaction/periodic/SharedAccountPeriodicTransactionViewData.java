//package use_case.transaction.periodic;
//
//import entity.account.SharedAccount;
//import java.time.LocalDate;
//
///**
// * The SharedAccountPeriodicTransactionViewData class represents the view data for a
// * periodic transaction operation involving a shared account.
// */
//public class SharedAccountPeriodicTransactionViewData {
//    private SharedAccount sharedAccount;
//    private double transactionAmount;
//    private LocalDate transactionStartDate;
//    private LocalDate transactionEndDate;
//    private int transactionPeriod;
//    private String transactionDescription;
//    private LocalDate transactionDate;
//    private double newBalance;
//
//    /**
//     * Constructs a SharedAccountPeriodicTransactionViewData object with the specified details.
//     *
//     * @param sharedAccount           the shared account for the transaction
//     * @param transactionAmount       the amount of the transaction
//     * @param transactionStartDate    the start date of the transaction
//     * @param transactionEndDate      the end date of the transaction
//     * @param transactionPeriod       the period of the transaction
//     * @param transactionDescription  the description of the transaction
//     * @param transactionDate         the date of the transaction
//     * @param newBalance              the new balance after the transaction
//     */
//    public SharedAccountPeriodicTransactionViewData(SharedAccount sharedAccount, double transactionAmount, LocalDate transactionStartDate, LocalDate transactionEndDate,
//                                                    int transactionPeriod, String transactionDescription, LocalDate transactionDate, double newBalance) {
//        this.sharedAccount = sharedAccount;
//        this.transactionAmount = transactionAmount;
//        this.transactionStartDate = transactionStartDate;
//        this.transactionEndDate = transactionEndDate;
//        this.transactionPeriod = transactionPeriod;
//        this.transactionDescription = transactionDescription;
//        this.transactionDate = transactionDate;
//        this.newBalance = newBalance;
//    }
//
//}