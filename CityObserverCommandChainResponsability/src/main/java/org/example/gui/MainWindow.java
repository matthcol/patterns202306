package org.example.gui;

import org.example.city.City;
import org.example.command.CommandOk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

    // model
    private City city;

    // GUI components
    private CityViewPanel cityViewPanel;
    private JButton jbtOk;
    private JButton jbtReset;
    private JButton jbtSave;
    private JButton jbtLoad;
    private JButton jbtExit;

    private JMenuItem itemChange;
    private JMenuItem itemReset;
    private JMenuItem itemSave;
    private JMenuItem itemLoad;
    private JMenuItem itemExit;


    public MainWindow() {
        super("City Editor");
        initComponents();
        initEvents();
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }



    private void initComponents() {
        this.setPreferredSize(new Dimension(400,400));
        var mainPanel = this.getRootPane();
        mainPanel.setLayout(new BorderLayout());
        // city view panel
        cityViewPanel = new CityViewPanel();
        mainPanel.add(BorderLayout.CENTER, cityViewPanel);
        // control buttons
        var panelButtons  = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        jbtOk = new JButton("OK");
        panelButtons.add(jbtOk);
        jbtReset = new JButton("Reset");
        panelButtons.add(jbtReset);
        jbtSave = new JButton("Save");
        panelButtons.add(jbtSave);
        jbtLoad = new JButton("Load");
        panelButtons.add(jbtLoad);
        jbtExit = new JButton("Exit");
        panelButtons.add(jbtExit);
        mainPanel.add(BorderLayout.SOUTH, panelButtons);
        // menu bar
        var menuBar = new JMenuBar();
        var menuFile = new JMenu("Edit");
        itemLoad = new JMenuItem("Load");
        menuFile.add(itemLoad);
        itemSave = new JMenuItem("Save");
        menuFile.add(itemSave);
        itemExit = new JMenuItem("Exit");
        menuFile.add(itemExit);
        menuBar.add(menuFile);
        var menuEdit = new JMenu("Edit");
        itemChange = new JMenuItem("Change");
        itemChange.setAccelerator(KeyStroke.getKeyStroke(
                "control M"));
        menuEdit.add(itemChange);
        itemReset = new JMenuItem("Reset");
        menuEdit.add(itemReset);
        itemReset.setAccelerator(KeyStroke.getKeyStroke(
                "control Z"));
        menuBar.add(menuEdit);
        menuBar.setVisible(true);
        this.setJMenuBar(menuBar);
    }

    private void initEvents() {
        // with ConcreteClass implementing interface command ActionListener
        // command Ok
        ActionListener commandOk = CommandOk.of(this)
        jbtOk.addActionListener(commandOk);
        itemChange.addActionListener(commandOk);

        // Java 8+: with a function compatible with interface command ActionListener
        // command Reset
        ActionListener commandReset = actionEvent -> updateViewPanelFromModel();
        jbtReset.addActionListener(commandReset);
        itemReset.addActionListener(commandReset);
    }

    public void setModel(City city) {
        // register views as observers
        this.city = city;
        cityViewPanel.setCity(city);
    }

    // receive
    public void updateModelFromViewPanel() {
        City cityTemp = cityViewPanel.getCity();
        city.update(cityTemp);
    }

    public void updateViewPanelFromModel() {
        cityViewPanel.setCity(city);
    }
}
