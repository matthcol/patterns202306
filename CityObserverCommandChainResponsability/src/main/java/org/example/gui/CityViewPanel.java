package org.example.gui;

import org.example.city.City;

import javax.swing.*;
import java.awt.*;

public class CityViewPanel extends JPanel {
    // initial city, can be changed later
    private City city;

    // widgets
    private JTextField jtfName;
    private JSpinner jspnPopulation;
    private JTextField jtfRegion;
    public CityViewPanel() {
        this.setLayout(new GridLayout(0,2));
        this.add(new JLabel("Name"));
        jtfName = new JTextField("                        ");
        this.add(jtfName);
        this.add(new JLabel("Population"));
        jspnPopulation = new JSpinner();
        this.add(jspnPopulation);
        this.add(new JLabel("Region"));
        jtfRegion = new JTextField("                        ");
        this.add(jtfRegion);
    }

    public void setCity(City city) {
        this.city = city;
        jtfName.setText(city.getName());
        jspnPopulation.setValue(city.getPopulation());
        jtfRegion.setText(city.getRegion());
    }
}
