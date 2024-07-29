package app;

import interface_adaptors.*;
import view.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            ViewManagerModel viewManagerModel = new ViewManagerModel();
            viewManagerModel.changeView("home page");


            viewManagerModel.addPropertyChangeListener(evt -> {
                String newViewName = (String) evt.getNewValue();
                viewManagerModel.changeView(newViewName);
            });


        });
    }
}
