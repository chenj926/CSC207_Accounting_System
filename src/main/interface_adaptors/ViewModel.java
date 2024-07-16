package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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