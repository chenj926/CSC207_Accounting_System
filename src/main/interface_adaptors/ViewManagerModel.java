package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveViewName() {
        return activeViewName;
    }

    public void setActiveViewName(String activeViewName) {
        String oldActiveViewName = this.activeViewName;
        this.activeViewName = activeViewName;
        support.firePropertyChange(activeViewName, oldActiveViewName, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // remove
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}