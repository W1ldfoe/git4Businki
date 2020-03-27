//Варіант №1
//Богак Юлія
//ПП-11
package com.company;
import javax.swing.*;
import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    private JFrame frame;
    private JTextField field;
    private JTextField[] dynamic_field;
    private Font font;
    private Font font1;

    private JButton button1;
    private JPanel panel;
    private GridBagConstraints constraints;

    private int N;
    private int[] Array;

    public Main() {
        GUI();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void GUI() {

        Color color = new Color(213, 208, 226);
        frame = new JFrame("Модульна контрольна робота");
        frame.setVisible(true);
        frame.setSize(600,550);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(color);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        font1 = new Font("Verdana", Font.BOLD|Font.ITALIC, 16);
        font = new Font("Verdana", Font.BOLD|Font.ITALIC, 14);

        JLabel label = new JLabel("Введіть розмір масиву");
        label.setForeground(Color.black);
        label.setFont(font);

        field = new JTextField("",15);
        field.setFont(font);
        field.setForeground(Color.black);

        JButton button = new JButton("Створити масив");
        button.setFont(font);
        button.setForeground(Color.black);
        button.setBackground(Color.WHITE);
        button.setActionCommand("button");
        button.addActionListener(new action());

        button1 = new JButton("Число, яке повторяється найчастіше");
        button1.setFont(font);
        button1.setForeground(Color.black);
        button1.setBackground(Color.WHITE);
        button1.setActionCommand("button1");
        button1.addActionListener(new action());

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.insets = new Insets(2, 10, 10, 10);

        panel.add(label, constraints);
        constraints.gridx = 1;
        panel.add(field, constraints);
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(button, constraints);
        panel.setBackground(color);

        frame.add(panel, BorderLayout.BEFORE_FIRST_LINE);
        frame.add(new JScrollPane(panel));
        frame.setVisible(true);
    }


    private class action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.equals("button")) {
                N = Integer.parseInt(field.getText());

                Array = new int[N];
                dynamic_field = new JTextField[N];
                JLabel[] f = new JLabel[N];
                constraints.insets = new Insets(2, 10, 10, 10);
                for (int i = 0; i < N; i++) {
                    f[i] = new JLabel("Введіть елемент № " + (i + 1));
                    dynamic_field[i] = new JTextField();
                    dynamic_field[i].setFont(font);
                    constraints.gridx = 0;
                    constraints.gridy = (3 + i);
                    panel.add(f[i], constraints);
                    constraints.gridx = 1;
                    constraints.gridy = (3 + i);
                    panel.add(dynamic_field[i], constraints);
                }
                constraints.gridx = 0;
                constraints.gridy = (N+5);
                constraints.gridwidth = 2;
                panel.add(button1, constraints);
                frame.validate();
            }

            String command1 = e.getActionCommand();

            if (command1.equals("button1")){
                for (int i = 0; i<N; i++) {
                    Array[i] = Integer.parseInt(dynamic_field[i].getText());
                }

                JLabel max_label = new JLabel();
                max_label.setFont(font1);
                max_label.setForeground(Color.black);
                max_label.setHorizontalAlignment(SwingConstants.CENTER);

                int n = 1;
                int c = 0;

                int n_max = 0;
                StringBuilder c_max = new StringBuilder();

                for(int i = 0; i<N;i++) {

                    for(int k = i+1; k<N; k++) {
                        if (Array[i] == Array[k]) {
                            n = n+1;
                            c = Array[i];
                        }
                    }

                    if(n > n_max){
                        n_max = n;
                        c_max = new StringBuilder("" + c);
                        n = 1;
                    }
                    else if( n < n_max ){
                        n = 1;
                        c = 0;
                    }
                    else if (n == n_max){
                        c_max.append(", ").append(c);
                        n = 1;
                    }
                }
                if(n_max !=1) {
                    max_label.setText("Найчастіше (а саме " + n_max + " рази) зустрічається число " + c_max);
                } else {
                    max_label.setText("Всі числа введені однакову кількість раз");
                }
                constraints.gridy = (N + 6);
                constraints.gridwidth = 2;
                panel.add(max_label, constraints);
                frame.validate();
            }

        }
    }
}