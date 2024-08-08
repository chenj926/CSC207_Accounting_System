package app.main;

import interface_adaptors.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {;

            ViewManagerModel viewManagerModel = new ViewManagerModel();
            viewManagerModel.changeView("home page");

            // Listen for changes in the active view name and switch views accordingly
            viewManagerModel.addPropertyChangeListener(evt -> {
                String newViewName = (String) evt.getNewValue();
                // Only pass the viewModel if it is needed for the new view
                viewManagerModel.changeView(newViewName);
            });
        });
    }
}
