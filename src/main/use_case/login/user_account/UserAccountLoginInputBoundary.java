package use_case.login.user_account;

import use_case.login.LoginInputBoundary;

/**
 * The LoginInputBoundary interface provides a method for executing login operations.
 * Implementations of this interface will handle the login process using the provided input data.
 *
 * @author Dana
 */
public interface UserAccountLoginInputBoundary extends LoginInputBoundary<UserAccountLoginInputData> {
}