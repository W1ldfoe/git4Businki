package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Diagram extends JPanel {
    private ArrayList<String> groupName;
    private ArrayList <Integer> studentsAmount;
    private int height;
    private int width;
    private int widthRect;
    private int heightRect;

    Diagram(ArrayList <String> groupName, ArrayList <Integer> studentsAmount) {
        this.groupName = groupName;
        this.studentsAmount = studentsAmount;
    }
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        height = (int)(screenHeight*0.8);
        width = (int)(screenWidth*0.8);

        g2.setFont( new Font("Times New Roman", Font.BOLD, 25));
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);
        Color color = new Color(R, G, B);

        g2.setColor(color);

        widthRect = 80;
        for(int i = 0; i < groupName.size(); i++) {
            heightRect = studentsAmount.get(i) * 18;
            g2.drawString(studentsAmount.get(i).toString(), (widthRect + 30) * i + 20,height - heightRect - 110);
            g2.fillRect((widthRect + 30) * i + 20, height - heightRect - 100, widthRect, heightRect);
            g2.rotate(Math.PI / 4.0,(widthRect + 30) * i + 20,height - 90);
            g2.drawString(groupName.get(i), (widthRect + 30) * i + 20,height - 90);
            g2.rotate(-Math.PI / 4.0, (widthRect + 30) * i + 20,height - 90);
        }
    }
}