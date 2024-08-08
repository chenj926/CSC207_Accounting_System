package view.financial_report;

import interface_adaptors.financial_report.UserAccountFinancialReportController;
import interface_adaptors.financial_report.UserAccountFinancialReportViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * The panel for displaying the financial report. This class is responsible for setting up
 * the UI components, updating the view with report data, and handling user interactions.
 *
 * @author Eric Chen
 */
public class FinancialReportPanel extends JPanel {
    private final UserAccountFinancialReportViewModel viewModel;
    private final UserAccountFinancialReportController userAccountFinancialReportController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
    private JLabel incomeLabel;
    private JLabel outflowLabel;
    private JLabel balanceLabel;

    private JTextArea reportTextArea;
    private JButton backButton;
    private JScrollPane scrollPane;

    /**
     * Constructs a FinancialReportPanel with the specified view model, controller, and view manager.
     *
     * @param viewModel the view model to be used
     * @param userAccountFinancialReportController the controller to handle report actions
     * @param viewManager the view manager to handle view changes
     */
    public FinancialReportPanel(UserAccountFinancialReportViewModel viewModel,
                                UserAccountFinancialReportController userAccountFinancialReportController,
                                ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.userAccountFinancialReportController = userAccountFinancialReportController;
        this.viewManager = viewManager;
        this.reportTextArea = new JTextArea();

        this.viewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())){
                this.reportTextArea.setText(viewModel.getReportContent());

            }
        });

        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the UI components of the panel.
     */
    private void initializeComponents() {
        this.titleLabel = new JLabel(this.viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.reportTextArea.setSelectionColor(Color.BLUE);
        this.reportTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        this.reportTextArea.setEditable(false);

        this.scrollPane = new JScrollPane(this.reportTextArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    /**
     * Sets up the layout and adds the components to the panel.
     */
    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        add(this.titleLabel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(this.scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPanel.add(this.backButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the listeners for user interactions.
     */
    private void setupListeners() {
        this.backButton.addActionListener(e -> {
            viewManager.setActiveViewName("Homepage Two");
        });
    }

    /**
     * Refreshes the data by executing the financial report controller with the current user ID.
     */
    public void refreshData() {
        userAccountFinancialReportController.execute(viewManager.getUserId());
    }

    /**
     * Clears the report text area.
     */
    public void clearFields() {
        reportTextArea.setText("");
    }

}
