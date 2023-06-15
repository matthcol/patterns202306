package org.example.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor(staticName = "of")
public class CommandModify implements ActionListener {
    @NonNull
    private MainWindow mainWindow;

    @Override
    public void actionPerformed(ActionEvent e) {
        // log
        System.out.println("Command Modify called");
        // call receiver
        mainWindow.updateModelFromViewPanel();
    }
}
