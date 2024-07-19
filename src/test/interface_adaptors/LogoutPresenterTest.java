package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.LogoutOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogoutPresenterTest {
    private LogoutViewModel logoutViewModel;
    private ViewManagerModel viewManagerModel;
    private LogoutPresenter logoutPresenter;

    @BeforeEach
    void setUp() {
        logoutViewModel = new LogoutViewModel();
        viewManagerModel = new ViewManagerModel() {
            @Override
            public void setActiveView(String viewName) {

            }
        };
        logoutPresenter = new LogoutPresenter(logoutViewModel, viewManagerModel);
    }

}