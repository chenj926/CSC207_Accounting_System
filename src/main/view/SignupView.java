package view;

import interface_adaptors.*;
import use_case.SignupInteractor;
import data_access.*;
import entity.*;
import use_case.SignupOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JFrame implements PropertyChangeListener {
    private final SignupViewModel viewModel;
    private final SignupPanel signupPanel;

    public SignupView(SignupViewModel viewModel, SignupController signupController, ViewManagerModel viewManager) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        signupPanel = new SignupPanel(viewModel, signupController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(signupPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (!state.isValid()) {
            JOptionPane.showMessageDialog(this, state.getStateError());
        }
        else {
            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
        }
    }

}
