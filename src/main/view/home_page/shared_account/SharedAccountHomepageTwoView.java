package view.home_page.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoController;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoState;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SharedAccountHomepageTwoView extends JFrame implements PropertyChangeListener {
    private final SharedAccountHomepageTwoViewModel viewModel;
    private final SharedAccountHomepageTwoPanel shareAccountHomepageTwoPanel;
    private SharedAccountHomepageTwoController controller;
    private final ViewManagerModel viewManager;

    public SharedAccountHomepageTwoView(SharedAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManager,
                                         SharedAccountHomepageTwoController controller) {
        super("Shared Account Homepage Two");
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.viewManager = viewManager;
        this.controller = controller;

        this.shareAccountHomepageTwoPanel = new SharedAccountHomepageTwoPanel(this.viewModel, this.viewManager, this.controller);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    /**
     * Sets up the user interface by adding the transaction panel to the content pane.
     */
    private void setupUI() {
        this.getContentPane().add(shareAccountHomepageTwoPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here if needed
        SharedAccountHomepageTwoState state = (SharedAccountHomepageTwoState) evt.getNewValue();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            String id = this.viewManager.getUserId();
            this.controller.execute(id);
        }
    }
}
