package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JFrame implements PropertyChangeListener {
    private LoginViewModel viewModel;
    private LoginPanel loginPanel;

    public LoginView(LoginViewModel viewModel) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        loginPanel = new LoginPanel(viewModel);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(loginPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            // Handle state changes if needed
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new LogInView(new LoginViewModel()));
//    }
}
