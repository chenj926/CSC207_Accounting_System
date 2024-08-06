package view.logout;

import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LogoutPanel class represents the user interface for the logout functionality.
 * It provides buttons for logging out and canceling the logout action.
 *
 * @author Jessica
 * @author Dana
 */
public class LogoutPanel extends JPanel {
    private final LogoutViewModel viewModel;
    private LogoutController logoutController;

    private JLabel titleLabel;
    private JButton logoutButton;
    private JButton cancelButton;

    /**
     * Constructs a LogoutPanel object with the specified view model and logout controller.
     *
     * @param viewModel       the view model for the logout panel
     * @param logoutController the controller handling logout actions
     */
    public LogoutPanel(LogoutViewModel viewModel, LogoutController logoutController) {
        this.viewModel = viewModel;
        this.logoutController = logoutController;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the components of the logout panel, including labels and buttons.
     */
    private void initializeComponents() {
        this.titleLabel = new JLabel(viewModel.getTitleLabel());

        JPanel buttons = new JPanel();
        this.logoutButton = new JButton(this.viewModel.getLogoutButtonText());
        buttons.add(this.logoutButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButtonText());
        buttons.add(this.cancelButton);
    }

    /**
     * Sets up the user interface of the panel by arranging components using a GridBagLayout.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 10, 2, 10);  // pad

        // title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE; //this ensures the title label is not stretched horizontally
        add(titleLabel, constraints);

        // reset grid width and anchor for other components
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // logout button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(logoutButton, constraints);

        // cancel button
        constraints.gridy = 4;
        add(cancelButton, constraints);
    }

    /**
     * Sets up listeners for button actions.
     * Handles logout and cancel button actions.
     */
    private void setupListeners() {
        //logout button response action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(logoutButton)) {
                   // LogoutState currentState = viewModel.getState();
                   logoutController.execute(
                           "logout"
                   );
                }
            }
        });

        // cancel button response action
        cancelButton.addActionListener(e ->{
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });
    }
}
