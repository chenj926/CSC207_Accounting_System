package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class serves as a base class for view models in the application.
 * It provides support for property change notifications and stores the name of the view.
 *
 * @author Rita
 * @author Xile
 */
public abstract class ViewModel {
    protected String viewName;
    private final PropertyChangeSupport propertyChangeSupport;

    public ViewModel(String viewName) {
        this.viewName = viewName;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public String getViewName() {
        return this.viewName;
    }

    protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
}

