package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class ViewManagerModelTest {

    private ViewManagerModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new ViewManagerModel();
    }

    @Test
    void testGetActiveViewName() {
        assertNull(viewModel.getActiveViewName());
        viewModel.setActiveViewName("Home");
        assertEquals("Home", viewModel.getActiveViewName());
    }

    @Test
    void testSetActiveViewName() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.setActiveViewName("Home");
        assertEquals("Home", viewModel.getActiveViewName());
        assertTrue(listener.isNotified());
        assertEquals("Home", listener.getEvent().getPropertyName());
        assertNull(listener.getEvent().getOldValue());
        assertEquals("Home", listener.getEvent().getNewValue());

        listener.reset();
        viewModel.setActiveViewName("Dashboard");
        assertEquals("Dashboard", viewModel.getActiveViewName());
        assertTrue(listener.isNotified());
        assertEquals("Dashboard", listener.getEvent().getPropertyName());
        assertEquals("Home", listener.getEvent().getOldValue());
        assertEquals("Dashboard", listener.getEvent().getNewValue());
    }

    @Test
    void testAddRemovePropertyChangeListener() {
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        viewModel.setActiveViewName("Home");
        assertTrue(listener.isNotified());

        listener.reset();
        viewModel.removePropertyChangeListener(listener);
        viewModel.setActiveViewName("Dashboard");
        assertFalse(listener.isNotified());
    }

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

