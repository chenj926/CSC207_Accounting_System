package use_case.transaction.periodic;

import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.account.UserAccount;
import use_case.transaction.TransactionInteractor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

/**
 * The SharedAccountPeriodicTransactionInteractor class is responsible for handling periodic transactions
 * associated with a SharedAccount.
 *
 */

