package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JFrame implements PropertyChangeListener {
    private LoginViewModel viewModel;
    private LoginPanel loginPanel;

    public LoginView(LoginViewModel viewModel, LoginController loginController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        loginPanel = new LoginPanel(viewModel, loginController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(loginPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getSuccessMsg() == null) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        } else {
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            loginPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}
