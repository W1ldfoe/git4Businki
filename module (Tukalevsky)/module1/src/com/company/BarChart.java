package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

class BarChart extends JPanel {
    private static final Color Background = Color.white;
    private static final Color Bar = Color.black;

    private LinkedList<Integer> inputLengthData;
    private LinkedList<String> inputRiverData;

    public BarChart(LinkedList<Integer> inputLengthData, LinkedList<String> inputRiverData) {
        this.inputLengthData = inputLengthData;
        this.inputRiverData = inputRiverData;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        BarDraw(g);
    }

    private void BarDraw(final Graphics g) {
        int margin = 20;
        int width = 800 + 2 * margin;
        int height = 600 + 2 * margin;
        int scale = 1;

        for (int i = 0; i < inputLengthData.size();i++){
            int temp = Integer.toString(inputLengthData.get(i)).length();
            if (temp > scale) {
                scale = temp;
            }
        }

        scale = (int) Math.pow(10,scale) /10;
        g.setColor(Background);
        g.fillRect(0, 0, width, height);

        final int barWidth = 70;
        for (int i = 0; i < inputLengthData.size() && i < inputRiverData.size(); i++) {
            final int x = margin + 80 * i;
            final int barHeight = 30 * inputLengthData.get(i) / scale;
            final int y = 350 - barHeight;
            g.setColor(Bar);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.black);

            g.drawString(Integer.toString(inputLengthData.get(i)), x + barWidth / 2, y - 5);

            JLabel companyName = new JLabel(this.inputRiverData.get(i));
            companyName.setBounds(x,y + barHeight,barWidth,20);
            this.add(companyName);
        }
    }
}
