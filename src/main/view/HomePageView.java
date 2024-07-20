package view;

import interface_adaptors.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageView extends JFrame implements PropertyChangeListener {
    private HomePageViewModel viewModel;
    private HomePagePanel homePagePanel;

    public HomePageView(HomePageViewModel viewModel) {
        super(viewModel.getTitleLabel());
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // homePagePanel = new HomePagePanel(viewModel, homePageController);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    private void setupUI() {
        this.getContentPane().add(homePagePanel, BorderLayout.CENTER);
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        HomePageState state = (HomePageState) evt.getNewValue();
//        if (state.getStateError() != null) {
//            JOptionPane.showMessageDialog(this, state.getStateError());
//        } else {
//            JOptionPane.showMessageDialog(this, state.getSuccessMsg());
//        }
//    }
//    }
}

