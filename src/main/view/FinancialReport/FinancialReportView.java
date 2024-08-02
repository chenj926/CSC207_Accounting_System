package view.FinancialReport;

import interface_adaptors.FinancialReport.FinancialReportController;
import interface_adaptors.FinancialReport.FinancialReportState;
import interface_adaptors.FinancialReport.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import interface_adaptors.transaction.periodic.PeriodicTransactionState;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;
import view.transaction.periodic.PeriodicTransactionPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FinancialReportView extends JFrame implements PropertyChangeListener {
    private FinancialReportPanel financialReportPanel;
    private FinancialReportViewModel viewModel;

    public FinancialReportView (FinancialReportViewModel viewModel,
                                FinancialReportController financialReportController,
                                ViewManagerModel viewManager) {
        super("Financial Report");
        this.viewModel = viewModel;
//        this.viewModel.addPropertyChangeListener(this);

        financialReportPanel = new FinancialReportPanel(viewModel, financialReportController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
//        this.getContentPane().add(financialReportPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        FinancialReportState state = (FinancialReportState) evt.getNewValue();
//        if (state.getErrorMsg() == null) {
//            String successMsg = state.getSuccessMessage();
//            System.out.println(successMsg);
//            JOptionPane.showMessageDialog(this, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            String errMsg = state.getErrorMsg();
//            JOptionPane.showMessageDialog(this, errMsg, "Error", JOptionPane.INFORMATION_MESSAGE);
//        }
    }

    /**
     * Makes the view visible and clears the fields in the periodic transaction panel when the view becomes visible.
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            financialReportPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
