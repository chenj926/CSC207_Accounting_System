package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogoutView extends JFrame implements PropertyChangeListener {
    private final LogoutViewModel viewModel;

    private JLabel titleLabel;
    private JButton logoutButton;
    private JButton cancelButton;

    public LogoutView(LogoutViewModel viewModel) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        titleLabel = new JLabel(viewModel.getTitleLabel());
        logoutButton = new JButton(viewModel.getLogoutButtonText());
        cancelButton = new JButton(viewModel.getCancelButtonText());

        setupUI();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 10, 2, 10);  // pad

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(logoutButton, constraints);

        constraints.gridy = 2;
        panel.add(cancelButton, constraints);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        logoutButton.addActionListener(e -> {
            viewModel.setState(new LogoutState());
            viewModel.firePropertyChanged();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            // Handle state changes if needed
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new LogoutView(new LogoutViewModel()));
//    }
}

