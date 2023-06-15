package org.example;

import org.example.city.City;
import org.example.city.observer.CityDisplayConsole;
import org.example.gui.MainWindow;

public class Main {
    public static void main(String[] args) {
        // GUI
        MainWindow mainWindow = new MainWindow();
        // Model
        City city = City.builder()
                .name("Toulouse")
                .population(470000)
                .region("Occitanie")
                .build();
        mainWindow.setModel(city);
        // observer in console
        CityDisplayConsole cityDisplayConsole = new CityDisplayConsole();
        city.register(cityDisplayConsole);
        // start GUI
        mainWindow.setVisible(true);
    }
}