package com.company;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new MainFrame("Модуль");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                System.exit (0);
            }
        });
    }
}
