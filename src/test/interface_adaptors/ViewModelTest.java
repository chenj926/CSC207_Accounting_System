package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelTest {

    private TestViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new TestViewModel("Test View");
    }

    @Test
    void testAddRemovePropertyChangeListener() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged("testProperty", null, "newValue");
        assertTrue(listener.isNotified());
        assertEquals("testProperty", listener.getEvent().getPropertyName());
        assertNull(listener.getEvent().getOldValue());
        assertEquals("newValue", listener.getEvent().getNewValue());

        listener.reset();
        viewModel.removePropertyChangeListener(listener);
        viewModel.firePropertyChanged("testProperty", null, "newValue");
        assertFalse(listener.isNotified());
    }

    @Test
    void testGetViewName() {
        assertEquals("Test View", viewModel.getViewName());
    }

    // Concrete subclass for testing purposes
    private static class TestViewModel extends ViewModel {
        public TestViewModel(String viewName) {
            super(viewName);
        }

        @Override
        public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
            super.firePropertyChanged(propertyName, oldValue, newValue);
        }
    }

    // PropertyChangeListener implementation for testing
    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private boolean notified = false;
        private PropertyChangeEvent event;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            this.notified = true;
            this.event = evt;
        }

        public boolean isNotified() {
            return notified;
        }

        public PropertyChangeEvent getEvent() {
            return event;
        }

        public void reset() {
            this.notified = false;
            this.event = null;
        }
    }
}


