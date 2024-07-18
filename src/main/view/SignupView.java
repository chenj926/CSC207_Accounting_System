package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JFrame implements PropertyChangeListener {
    private final SignupViewModel viewModel;
    private final SignupPanel signupPanel;

    public SignupView(SignupViewModel viewModel) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        signupPanel = new SignupPanel(viewModel);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(signupPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            // Respond to state change
        }
    }

    // Uncomment the main method to run the signup view standalone
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new SignupView(new SignupViewModel()));
//    }
}
