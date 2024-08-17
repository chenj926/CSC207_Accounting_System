package interface_adaptors.homepage;

import interface_adaptors.homepage.user_account.UserAccountHomepageTwoState;
import interface_adaptors.homepage.user_account.UserAccountHomepageTwoViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountHomePageTwoViewModelTest {

    private UserAccountHomepageTwoViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountHomepageTwoViewModel();
    }

    @Test
    void testGetViewName() {
        assertEquals("Homepage Two", viewModel.getViewName());
    }

    @Test
    void testGetTitleLabel() {
        assertEquals("User Account", viewModel.getTITLE_LABEL());
    }

    @Test
    void testGetSharedIdsLabel() {
        assertEquals("Related Shared Account Ids: ", viewModel.getSHAREDIDS_LABEL());
    }

    @Test
    void testGetAndSetState() {
        UserAccountHomepageTwoState state = new UserAccountHomepageTwoState();
        state.setTotalIncome("3000.00");
        state.setTotalOutflow("1500.00");
        state.setTotalBalance("1500.00");
        state.setUsername("testUser");
        state.setBasicUserInfo(new String[]{"testUser", "Basic Info"});

        viewModel.setState(state);

        assertEquals(state, viewModel.getState());
        assertEquals("3000.00", viewModel.getState().getTotalIncome());
        assertEquals("1500.00", viewModel.getState().getTotalOutflow());
        assertEquals("1500.00", viewModel.getState().getTotalBalance());
        assertEquals("testUser", viewModel.getState().getUsername());
        assertArrayEquals(new String[]{"testUser", "Basic Info"}, viewModel.getState().getBasicUserInfo());
    }

    @Test
    void testFirePropertyChanged() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        assertTrue(listener.isPropertyChanged());
    }

    @Test
    void testResetState() {
        UserAccountHomepageTwoState state = new UserAccountHomepageTwoState();
        state.setTotalIncome("3000.00");
        state.setTotalOutflow("1500.00");
        state.setTotalBalance("1500.00");
        state.setUsername("testUser");
        state.setBasicUserInfo(new String[]{"testUser", "Basic Info"});

        viewModel.setState(state);

        viewModel.resetState();

        UserAccountHomepageTwoState newState = viewModel.getState();

        assertNotEquals("3000.00", newState.getTotalIncome());
        assertNull(newState.getTotalIncome());
        assertNull(newState.getTotalOutflow());
        assertNull(newState.getTotalBalance());
        assertNull(newState.getUsername());
        assertNull(newState.getBasicUserInfo());
    }


    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private boolean propertyChanged = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyChanged = true;
        }

        public boolean isPropertyChanged() {
            return propertyChanged;
        }
    }
}
