package org.example.gui;

import org.example.city.City;
import org.example.command.CommandModify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    // model
    private City city;

    // GUI components
    private CityViewPanel cityViewPanel;
    private JButton jbtModify;
    private JButton jbtReset;
    private JButton jbtSave;
    private JButton jbtLoad;
    private JButton jbtExit;

    private JMenuItem itemModify;
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
        var mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.getContentPane().add(mainPanel);
        // city view panel
        cityViewPanel = new CityViewPanel();
        mainPanel.add(BorderLayout.CENTER, cityViewPanel);
        // control buttons
        var panelButtons  = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        jbtModify = new JButton("Modify");
        panelButtons.add(jbtModify);
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
        var menuFile = new JMenu("File");
        itemLoad = new JMenuItem("Load");
        menuFile.add(itemLoad);
        itemLoad.setAccelerator(KeyStroke.getKeyStroke(
                "control O"));
        itemSave = new JMenuItem("Save");
        itemSave.setAccelerator(KeyStroke.getKeyStroke(
                "control S"));
        menuFile.add(itemSave);
        itemExit = new JMenuItem("Exit");
        menuFile.add(itemExit);
        itemExit.setAccelerator(KeyStroke.getKeyStroke(
                "control Q"));
        menuBar.add(menuFile);
        var menuEdit = new JMenu("Edit");
        itemModify = new JMenuItem("Modify");
        itemModify.setAccelerator(KeyStroke.getKeyStroke(
                "control M"));
        menuEdit.add(itemModify);
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
        ActionListener commandOk = CommandModify.of(this);
        jbtModify.addActionListener(commandOk);
        itemModify.addActionListener(commandOk);

        // Java 8+: with a function compatible with interface command ActionListener
        // command Reset
        ActionListener commandReset = actionEvent -> updateViewPanelFromModel();
        jbtReset.addActionListener(commandReset);
        itemReset.addActionListener(commandReset);

        ActionListener commandExit = actionEvent -> this.dispose();
        itemExit.addActionListener(commandExit);

        itemSave.addActionListener(actionEvent -> System.out.println("TODO: save city"));
        itemLoad.addActionListener(actionEvent -> System.out.println("TODO: load city"));
    }

    public void setModel(City city) {
        // register views as observers
        this.city = city;
        cityViewPanel.setCity(city);
    }

    // receive
    public void updateModelFromViewPanel() {
        System.out.println("Update model from view");
        City cityTemp = cityViewPanel.getCity();
        city.update(cityTemp);
    }

    public void updateViewPanelFromModel() {
        System.out.println("Update view from model");
        cityViewPanel.setCity(city);
    }
}
