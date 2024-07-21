package view;

import interface_adaptors.PeriodicTransactionController;
import interface_adaptors.PeriodicTransactionState;
import interface_adaptors.PeriodicTransactionViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PeriodicTransactionView extends JFrame implements PropertyChangeListener {
    private PeriodicTransactionPanel periodicTransactionPanel;
    private PeriodicTransactionViewModel viewModel;

    public PeriodicTransactionView(PeriodicTransactionViewModel viewModel,
                                   PeriodicTransactionController periodicTransactionController,
                                   ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        periodicTransactionPanel = new PeriodicTransactionPanel(viewModel, periodicTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(periodicTransactionPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PeriodicTransactionState state = (PeriodicTransactionState) evt.getNewValue();
        if (state.getSuccessMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMsg());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMessage());
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            periodicTransactionPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
