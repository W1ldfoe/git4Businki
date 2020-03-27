package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

public class Main{

    static JPanel panel = new JPanel(null);

    public static void main(String args[]) {
        JFrame frame = Frame();

        LinkedList<Integer> values = new LinkedList<>();
        LinkedList<String> valuesStr = new LinkedList<>();

        frame.add(panel);

        JLabel river = new JLabel("Название реки: ");
        river.setBounds(170, 10,200,20);
        panel.add(river);
        JTextField riverInput = new JTextField("",15);
        riverInput.setBounds(300,10, 200, 20);
        panel.add(riverInput);

        JLabel length = new JLabel("Длина реки (км): ");
        length.setBounds(170,40,200,20);
        panel.add(length);
        JTextField lengthInput = new JTextField("",15);
        lengthInput.setBounds(300,40, 200, 20);
        panel.add(lengthInput);

        BarChart chart = new BarChart(values, valuesStr);
        chart.setPreferredSize(new Dimension(800, 500));
        chart.setBounds(0,100, 800,500);
        panel.add(chart);

        JButton submit = new JButton("Построить диаграмму");
        submit.setBounds(300, 70,200, 20);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (lengthInput.getText().equals("")
                        || riverInput.getText().equals("")
                        || Integer.parseInt(lengthInput.getText()) <= 0) {
                    return;
                }
                values.add( Integer.parseInt(lengthInput.getText()));
                valuesStr.add(riverInput.getText());
                chart.repaint();
                riverInput.setText("");
                lengthInput.setText("");
                if (values.size() >= 5 || valuesStr.size() >= 5){
                    Color bgColor = new Color(204,204,204);
                    riverInput.setEnabled(false);
                    lengthInput.setEnabled(false);
                    submit.setEnabled(false);
                    riverInput.setBackground(bgColor);
                    lengthInput.setBackground(bgColor);
                    submit.setBackground(bgColor);
                }
            }
        });
        panel.add(submit);


        frame.setVisible(true);
    }

    static JFrame Frame() {
        JFrame frame = new JFrame();
        int windowWidth = 800;
        int windowHeight = 500;

        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width / 2 - windowWidth/2, dimension.height / 2 - windowHeight/2, windowWidth, windowHeight);
        frame.setTitle("Модуль 9 вариант");
        return frame;
    }
}

