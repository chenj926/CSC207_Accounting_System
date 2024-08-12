package view.financial_report.user_account;

import interface_adaptors.financial_report.user_account.UserAccountFinancialReportController;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportState;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserAccountFinancialReportView extends JFrame implements PropertyChangeListener {
    private UserAccountFinancialReportPanel userAccountFinancialReportPanel;
    private UserAccountFinancialReportViewModel viewModel;
    private UserAccountFinancialReportController userAccountFinancialReportController;
    private ViewManagerModel viewManager;

    public UserAccountFinancialReportView(UserAccountFinancialReportViewModel viewModel,
                                          UserAccountFinancialReportController userAccountFinancialReportController,
                                          ViewManagerModel viewManager) {
        super("Financial Report");
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);
        this.userAccountFinancialReportController = userAccountFinancialReportController;

//        this.viewModel.addPropertyChangeListener(evt -> {
//            if ("state".equals(evt.getPropertyName())){
//
//            }
//        });



        this.userAccountFinancialReportPanel = new UserAccountFinancialReportPanel(this.viewModel, this.userAccountFinancialReportController,
                this.viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(this.userAccountFinancialReportPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserAccountFinancialReportState state = (UserAccountFinancialReportState) evt.getNewValue();
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
            this.userAccountFinancialReportController.execute(id);
        }
    }
}
