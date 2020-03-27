package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static JPanel box = new JPanel(null);

    private static JFrame mainFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(400, 300, 800, 600);
        jFrame.setTitle("Module Control PP12");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }

    public static void avNum(LinkedList<Integer> mark1, LinkedList<Integer> mark2, LinkedList<Integer> mark3, LinkedList<Integer> avNum) {
        int size = mark1.size();
        for (int i = 0; i < size; i++) {
            int a = (mark1.get(i) + mark2.get(i) + mark1.get(i)) / 3;
            avNum.add(a);
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = mainFrame();
        jFrame.setVisible(true);
        jFrame.add(box);

        LinkedList<Integer> mark1 = new LinkedList<>();
        LinkedList<Integer> mark2 = new LinkedList<>();
        LinkedList<Integer> mark3 = new LinkedList<>();
        LinkedList<Integer> avNum = new LinkedList<>();
        LinkedList<String> fio = new LinkedList<>();

        JLabel askFIO = new JLabel("Enter FIO: ");
        box.add(askFIO);
        JTextField getFIO = new JTextField(5);
        box.add(getFIO);

        JLabel askAOP = new JLabel("Enter mark from AOP: ");
        box.add(askAOP);
        JTextField getAOP = new JTextField(5);
        box.add(getAOP);

        JLabel askASD = new JLabel("Enter mark from ASD: ");
        box.add(askASD);
        JTextField getASD = new JTextField(5);
        box.add(getASD);

        JLabel askEng = new JLabel("Enter mark from English: ");
        box.add(askEng);
        JTextField getEng = new JTextField(5);
        box.add(getEng);

        JButton goAhead = new JButton("goAhead");
        box.add(goAhead);

        mainFrame inputting = new mainFrame(avNum, fio);
        box.add(inputting);

        ActionListener catchAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mark1.add(Integer.parseInt(getAOP.getText()));
                mark2.add(Integer.parseInt(getASD.getText()));
                mark3.add(Integer.parseInt(getEng.getText()));
                fio.add(getFIO.getText());
                inputting.repaint();
                getAOP.setText("");
                getASD.setText("");
                getEng.setText("");
                getFIO.setText("");
                if (mark1.size() >= 11 || mark2.size() >= 11 || mark3.size() >= 11 || fio.size() >= 11) {
                    getAOP.setEnabled(false);
                    getASD.setEnabled(false);
                    getEng.setEnabled(false);
                    getFIO.setEnabled(false);
                }
            }
        };
    }
}

class mainFrame extends JFrame {
    private static final Color BAR_COLOR = Color.green;
    private LinkedList<Integer> getAvMark;
    private LinkedList<String> getName;

    public mainFrame(LinkedList<Integer> getAvMark, LinkedList<String> getName) {
        this.getAvMark = getAvMark;
        this.getName = getName;
    }

    protected void paintComponent(final Graphics g) {
        super.paintComponents(g);
        paintBar(g);
    }

    private void paintBar(final Graphics g) {
        int OUTER_MARGIN = 20;
        int WIDTH = 800 + 2 * OUTER_MARGIN;
        int HEIGHT = 600 + 2 * OUTER_MARGIN;
    }

    private void drawBars(final Graphics g) {
        int OUTER_MARGIN = 7;

        final int barWidth = 20;
        for (int i = 0; i < getAvMark.size() && i < getName.size(); i++) {
            final int x = OUTER_MARGIN + 80 * i;
            final int barHeight = 2 * getAvMark.get(i);
            final int y = 300 - barHeight;
            g.setColor(BAR_COLOR);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(getAvMark.get(i)), x + barWidth / 2, y);
            JLabel student = new JLabel(this.getName.get(i));
            student.setBounds(x, y + barHeight, barWidth, 20);
            this.add(student);
        }
    }
}