package view.logout;

import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutState;
import interface_adaptors.logout.LogoutViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The LogoutView class represents the main view for the logout functionality of the application.
 * It displays a panel for handling logout actions and listens for property changes to update its state.
 *
 * @author Jessica
 * @author Dana
 */
public class LogoutView extends JFrame implements PropertyChangeListener {
    private final LogoutViewModel viewModel;
    private final LogoutPanel logoutPanel;

    /**
     * Constructs a LogoutView object with the specified view model and logout controller.
     *
     * @param viewModel       the view model for the logout view
     * @param logoutController the controller handling logout actions
     */
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

    /**
     * Sets up the user interface by adding the logout panel to the frame's content pane.
     */
    private void setupUI() {
        this.getContentPane().add(logoutPanel, BorderLayout.CENTER);
    }

    /**
     * Handles property change events to update the view based on changes in the logout state.
     * Displays a message dialog with the logout message from the state.
     *
     * @param evt the property change event containing the new logout state
     */
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

