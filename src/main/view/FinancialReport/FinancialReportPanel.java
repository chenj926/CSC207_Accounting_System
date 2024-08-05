package view.FinancialReport;

import interface_adaptors.FinancialReport.FinancialReportController;
import interface_adaptors.FinancialReport.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinancialReportPanel extends JPanel {
    private final FinancialReportViewModel viewModel;
    private final FinancialReportController financialReportController;
    private final ViewManagerModel viewManager;
//    private ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JLabel incomeLabel;
    private JLabel outflowLabel;
    private JLabel balanceLabel;

    private JTextArea reportTextArea;
    private JButton backButton;
    private JScrollPane scrollPane;

//    private JButton refreshButton;

    public FinancialReportPanel(FinancialReportViewModel viewModel,
                                FinancialReportController financialReportController,
                                ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.financialReportController = financialReportController;
        this.viewManager = viewManager;
        this.reportTextArea = new JTextArea();

//        // debug
        System.out.println("id in finan panel!!:\n看我！\n"+viewManager.getUserId());
        // 先关了看看
//        financialReportController.execute(viewManager.getUserId());  // get the financial report content first

        this.viewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())){
                this.reportTextArea.setText(viewModel.getReportContent());

            }
        });

//        setupListeners();
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
//        financialReportController.execute();
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


//        this.reportTextArea = new JTextArea(this.viewModel.getReportContent());


//        this.reportTextArea.setSelectionColor(Color.BLUE);
//        this.reportTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
//        this.reportTextArea.setEditable(false);

//        this.scrollPane = new JScrollPane(new JTextArea(this.viewModel.getReportContent()));
////        this.scrollPane = new JScrollPane(reportTextArea);
//
//
//        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // debug
        System.out.println("in panel report:\n"+this.viewModel.getReportContent());

//        this.reportTextArea = new JTextArea(this.viewModel.getReportContent());
        this.reportTextArea.setSelectionColor(Color.BLUE);
        this.reportTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        this.reportTextArea.setEditable(false);

        this.scrollPane = new JScrollPane(this.reportTextArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        add(this.titleLabel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(this.scrollPane, BorderLayout.CENTER);

//        centerPanel.add(this.scrollPane);
        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPanel.add(this.backButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        this.backButton.addActionListener(e -> {
            viewManager.setActiveViewName("Transaction");
        });

//        financialReportController.execute();
//        refreshButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                refreshReport();
//            }
//        });
    }

//    public void refreshReport() {
//        financialReportController.refreshReport();
//    }

    public void refreshData() {
        // debug
        System.out.println("id in finan panel!!:\n看我！\n" + viewManager.getUserId());
        financialReportController.execute(viewManager.getUserId());
    }

    public void clearFields() {
        reportTextArea.setText("");
    }

}
