package interface_adaptors.homepage;

import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoState;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountHomepageTwoViewModelTest {

    private SharedAccountHomepageTwoViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountHomepageTwoViewModel();
    }

    @Test
    void testGetViewName() {
        assertEquals("Shared Account Homepage Two", viewModel.getViewName());
    }

    @Test
    void testGetTitleLabel() {
        assertEquals("Shared Account", viewModel.getTITLE_LABEL());
    }

    @Test
    void testGetAndSetState() {
        SharedAccountHomepageTwoState state = new SharedAccountHomepageTwoState();
        state.setTotalIncome("1000.00");
        state.setTotalOutflow("500.00");
        state.setTotalBalance("500.00");
        state.setBasicUserInfo(new String[]{"User1", "Basic Info"});

        viewModel.setState(state);

        assertEquals(state, viewModel.getState());
        assertEquals("1000.00", viewModel.getState().getTotalIncome());
        assertEquals("500.00", viewModel.getState().getTotalOutflow());
        assertEquals("500.00", viewModel.getState().getTotalBalance());
        assertArrayEquals(new String[]{"User1", "Basic Info"}, viewModel.getState().getBasicUserInfo());
    }


    @Test
    void testResetState() {
        SharedAccountHomepageTwoState state = new SharedAccountHomepageTwoState();
        state.setTotalIncome("1000.00");
        state.setTotalOutflow("500.00");
        state.setTotalBalance("500.00");
        state.setBasicUserInfo(new String[]{"User1", "Basic Info"});

        viewModel.setState(state);

        viewModel.resetState();

        SharedAccountHomepageTwoState newState = viewModel.getState();

        assertNotEquals("1000.00", newState.getTotalIncome());
        assertEquals(null, newState.getTotalIncome());
        assertEquals(null, newState.getTotalOutflow());
        assertEquals(null, newState.getTotalBalance());
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
