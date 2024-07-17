package interface_adaptors;

public class TransactionState {
    private boolean isOneTimeSelected;
    private boolean isPeriodicSelected;

    public boolean isOneTimeSelected() {
        return isOneTimeSelected;
    }

    public void setOneTimeSelected(boolean oneTimeSelected) {
        isOneTimeSelected = oneTimeSelected;
    }

    public boolean isPeriodicSelected() {
        return isPeriodicSelected;
    }

    public void setPeriodicSelected(boolean periodicSelected) {
        isPeriodicSelected = periodicSelected;
    }
}
