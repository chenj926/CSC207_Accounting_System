package view.financial_report;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportState;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SharedAccountFinancialReportView extends JFrame implements PropertyChangeListener {
    private SharedAccountFinancialReportPanel sharedAccountFinancialReportPanel;
    private final SharedAccountFinancialReportViewModel viewModel;
    private final SharedAccountFinancialReportController controller;
    private final ViewManagerModel viewManager;


    public SharedAccountFinancialReportView(SharedAccountFinancialReportViewModel viewModel,
                                            SharedAccountFinancialReportController controller,
                                            ViewManagerModel viewManager){
        super("Shared Account Financial Report");
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);
        this.controller = controller;

        this.sharedAccountFinancialReportPanel = new SharedAccountFinancialReportPanel(this.viewModel, this.controller, this.viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(this.sharedAccountFinancialReportPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SharedAccountFinancialReportState state = (SharedAccountFinancialReportState) evt.getNewValue();
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
            String id = this.viewManager.getUserId();
            this.controller.execute(id);
        }
    }

}
