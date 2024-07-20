package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogoutView extends JFrame implements PropertyChangeListener {
    private final LogoutViewModel viewModel;
    private final LogoutPanel logoutPanel;

    public LogoutView(LogoutViewModel viewModel, LogoutController logoutController) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        logoutPanel = new LogoutPanel(viewModel, logoutController);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(logoutPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LogoutState state = (LogoutState) evt.getNewValue();
        if (state.getStateError() != null) {
            JOptionPane.showMessageDialog(this, state.getLogoutMessage());
            // Handle state changes if needed
        } else {
            JOptionPane.showMessageDialog(this, state.getLogoutMessage());
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new LogoutView(new LogoutViewModel()));
//    }
}

