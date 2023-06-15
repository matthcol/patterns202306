package org.example;

import org.example.city.City;
import org.example.city.observer.CityDisplayConsole;
import org.example.gui.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Model
        City city = City.builder()
                .name("Toulouse")
                .population(470000)
                .region("Occitanie")
                .build();
        // observer in console
        CityDisplayConsole cityDisplayConsole = new CityDisplayConsole();
        city.register(cityDisplayConsole);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // GUI
                MainWindow mainWindow = new MainWindow();
                mainWindow.setModel(city);
                // start GUI
                mainWindow.setVisible(true);
            }
        });
    }
}