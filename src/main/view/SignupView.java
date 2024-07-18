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

    public SignupView(SignupViewModel viewModel, SignupController signupController) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        signupPanel = new SignupPanel(viewModel, signupController);

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

    // Uncomment the main method to run the signup view standalone
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create models
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            SignupViewModel signupViewModel = new SignupViewModel();

            // Create data access object
            UserSignupDataAccessInterface dataAccessObject = new InMemoryUserAccountDataAccessObject();

            // Create presenter
            SignupOutputBoundary presenter = new SignupPresenter(viewManagerModel, signupViewModel);

            // Create account factory
            AccountFactory accountFactory = new AccountFactory();

            // Create interactor
            SignupInteractor interactor = new SignupInteractor(dataAccessObject, presenter, accountFactory);

            // Create controller
            SignupController signupController = new SignupController(interactor);

            // Create view
            new SignupView(signupViewModel, signupController);
        });
    }
}
