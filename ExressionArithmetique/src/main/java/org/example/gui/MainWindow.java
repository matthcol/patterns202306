package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("Arithmetic Expression");
        initComponents();
        initEvents();
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }



    private void initComponents() {
        this.setPreferredSize(new Dimension(1920,1080));
    }

    private void initEvents() {
    }
}
