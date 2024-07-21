package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OneTimeTransactionView extends JFrame implements PropertyChangeListener {
    private OneTimeTransactionPanel oneTimeTransactionPanel;
    private OneTimeTransactionViewModel viewModel;

    public OneTimeTransactionView(OneTimeTransactionViewModel viewModel,
                                  OneTimeTransactionController oneTimeTransactionController,
                                  ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        oneTimeTransactionPanel = new OneTimeTransactionPanel(viewModel, oneTimeTransactionController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(oneTimeTransactionPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        OneTimeTransactionState state = (OneTimeTransactionState) evt.getNewValue();
        if (state.getSuccessMessage() == null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMessage());
        }
    }

}
