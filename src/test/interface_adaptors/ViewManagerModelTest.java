package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class ViewManagerModelTest {

    private ViewManagerModel viewManager;
    private TestViewModel testViewModel;

    @BeforeEach
    void setUp() {
        viewManager = new ViewManagerModel();
        testViewModel = new TestViewModel("Test View");
    }

    @Test
    void testGetAndSetUserId() {
        String userId = "user123";
        viewManager.setUserId(userId);
        assertEquals(userId, viewManager.getUserId());
    }

    @Test
    void testGetAndSetActiveViewName() {
        String viewName = "home page";
        viewManager.setActiveViewName(viewName);
        assertEquals(viewName, viewManager.getActiveViewName());
    }

    @Test
    void testAddAndRemovePropertyChangeListener() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewManager.addPropertyChangeListener(listener);

        viewManager.setActiveViewName("home page");
        assertTrue(listener.isPropertyChanged());

        listener.reset();
        viewManager.removePropertyChangeListener(listener);
        viewManager.setActiveViewName("sign up");
        assertFalse(listener.isPropertyChanged());
    }

    @Test
    void testChangeView() {
        viewManager.changeView("home page");
        assertEquals("home page", viewManager.getActiveViewName());

        viewManager.changeView("sign up");
        assertEquals("sign up", viewManager.getActiveViewName());
    }

    @Test
    void testViewModelGetViewName() {
        assertEquals("Test View", testViewModel.getViewName());
    }

    @Test
    void testViewModelFirePropertyChanged() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        testViewModel.addPropertyChangeListener(listener);

        testViewModel.changeProperty("testProperty", "oldValue", "newValue");
        assertTrue(listener.isPropertyChanged());

        listener.reset();
        testViewModel.removePropertyChangeListener(listener);
        testViewModel.changeProperty("testProperty", "oldValue", "newValue");
        assertFalse(listener.isPropertyChanged());
    }

    @Test
    void testCreateView() {
        // Test creating and changing to multiple views
        String[] viewNames = {
                "home page", "sign up", "log in", "Transaction",
                "One Time Transaction", "Periodic Transaction", "log out"
        };

        for (String viewName : viewNames) {
            viewManager.changeView(viewName);
            assertEquals(viewName, viewManager.getActiveViewName());
        }
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

        public void reset() {
            propertyChanged = false;
        }
    }

    private static class TestViewModel extends ViewModel {
        public TestViewModel(String viewName) {
            super(viewName);
        }

        public void changeProperty(String propertyName, Object oldValue, Object newValue) {
            firePropertyChanged(propertyName, oldValue, newValue);
        }
    }
}




