package com.company;

import javax.swing.*;
import java.awt.*;

public class InfoLabel extends JLabel {
    InfoLabel(String title) {
        super(title);
        Font labelFont = new Font("Times New Roman", Font.PLAIN, 20);
        setFont(labelFont);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalAlignment(JLabel.RIGHT);
    }
}
