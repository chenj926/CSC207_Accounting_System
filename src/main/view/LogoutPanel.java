package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;

public class LogoutPanel extends JPanel {
    private final LogoutViewModel viewModel;

    private JLabel titleLabel;
    private JButton logoutButton;
    private JButton cancelButton;

    public LogoutPanel(LogoutViewModel viewModel) {
        this.viewModel = viewModel;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    private void initializeComponents() {
        titleLabel = new JLabel(viewModel.getTitleLabel());
        logoutButton = new JButton(viewModel.getLogoutButtonText());
        cancelButton = new JButton(viewModel.getCancelButtonText());
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 10, 2, 10);  // pad

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(titleLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        add(logoutButton, constraints);

        constraints.gridy = 2;
        add(cancelButton, constraints);
    }

    private void setupListeners() {
        logoutButton.addActionListener(e -> {
            viewModel.setState(new LogoutState());
            viewModel.firePropertyChanged();
        });

        cancelButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });
    }
}
