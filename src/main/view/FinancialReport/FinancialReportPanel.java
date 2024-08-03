package view.FinancialReport;

import interface_adaptors.FinancialReport.FinancialReportController;
import interface_adaptors.FinancialReport.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;

import javax.swing.*;
import java.awt.*;

public class FinancialReportPanel extends JPanel {
    private final FinancialReportViewModel viewModel;
    private final FinancialReportController financialReportController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JComboBox<String> periodComb;
    private JComboBox<String> categoryButton;

    public FinancialReportPanel(FinancialReportViewModel viewModel,
                                FinancialReportController financialReportController,
                                ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.financialReportController = financialReportController;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);



    }

    private void setupUI() {

    }

    private void setupListeners() {

    }

    public void clearFields() {

    }
}
