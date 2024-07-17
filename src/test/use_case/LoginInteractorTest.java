package use_case;

import entity.AccountFactory;
import entity.UserAccount;
import data_access.LoginDataAccessInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LoginInteractorTest {

    private LoginInteractor loginInteractor;
    private LoginDataAccessInterfaceStub userDataAccessObject;
    private LogInOutputBoundaryStub presenter;
    private AccountFactory accountFactory;



