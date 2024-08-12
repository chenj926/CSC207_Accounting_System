package use_case;

import org.junit.jupiter.api.Test;
import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginInputDataTest {
    private LoginInteractorTest.SimplePresenter presenter;
    private class ConcreteLoginInputData extends LoginInputData {
        public ConcreteLoginInputData(String identification, String password) {
            super(identification, password);
        }
    }

    @Test
    public void testConstructorAndGetters() {
        String password = "password123";
        String identification = "user123";
        presenter = new LoginInteractorTest.SimplePresenter();
        ConcreteLoginInputData inputData = new ConcreteLoginInputData(identification, password);

        assertEquals(password, inputData.getPassword());
        assertEquals(identification, inputData.getIdentification());
    }
    private static class SimplePresenter implements LoginOutputBoundary {
        private String message;
        private LoginOutputData data;
        private boolean success;

        @Override
        public void prepareSuccessView(Object user) {

        }

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }


        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public LoginOutputData getData() {
            return data;
        }
    }
}


