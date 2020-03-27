package com.company;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private String a, b;
    private Integer result;
    public String resul="";

    public static void main(String[] args){
        Gui app = new Gui();
        app.setVisible(true);
    }
     Main(String d, String e) {
        this.a = d;
        this.b = e;
        other();
    }
    public void setResult(int a) {
        resul += a;
    }

    public String getResult() {
        return resul;
    }


    private void other() {
        ArrayList<Integer> firstArr = new ArrayList<>();
        ArrayList<Integer> secondArr = new ArrayList<>();

        parseDistinctElements(a, firstArr);

        parseDistinctElements(b, secondArr);

        firstArr.retainAll(secondArr);
        firstArr.sort(Comparator.naturalOrder());
        firstArr.forEach(i ->setResult(i));

    }


    public static void parseDistinctElements(String line, List<Integer> list) {
        for (String element : line.split(" ")) {
            int i = Integer.parseInt(element);
            if (!list.contains(i)) {
                list.add(i);
            }
        }
    }
}

class Gui extends JFrame {
    private JButton button = new JButton("Check");
    private JButton button1 = new JButton("Clear");
    private JTextField input = new JTextField("", 10);
    private JTextField input1 = new JTextField("", 10);
    private JLabel label3 = new JLabel("Нельзя вводить два пробела!!!");
    private JLabel label = new JLabel("Введите элементы первого массива через пробел:");
    private JLabel label1 = new JLabel("Введите элементы второго массива через пробел:");

    public Gui() {super("app");
        ImageIcon icon2 = new ImageIcon("src/welcome.gif");
        JOptionPane.showMessageDialog(null, " ",
                "Customized Dialog", JOptionPane.INFORMATION_MESSAGE, icon2);
        this.setBounds(100, 100, 1000, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        button.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 24));
        button1.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 24));
        getContentPane().setBackground(Color.PINK);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 0 , 6, 12));
        label.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 18));
        container.add(label);
        input.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 18));
        container.add(input);
        label1.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 18));
        container.add(label1);
        input1.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 18));
        container.add(input1);

        button.addActionListener(new ButtonEventListener());
        container.add(button);
        container.add(button1);
        this.revalidate();
        input.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_SPACE)) {
                    e.consume();  // разрешаем цифры и знак "+"
                }
            }
        });
        input1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_SPACE)) {
                    e.consume();  // разрешаем цифры и знак "+"
                }
            }
        });
        label3.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 30));
        label3.setForeground(Color.red);
        container.add(label3);
    }
    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ImageIcon icon = new ImageIcon("src/turtle64.png");
            JOptionPane.showMessageDialog(null, "I like a turtle. Wait please!",
                    "Customized Dialog", JOptionPane.INFORMATION_MESSAGE, icon);
            Main main = new Main(input.getText(), input1.getText());
            String message = "";
            message += "Первый массив: " + input.getText() + "\n";
            message += "Второй массив:" + input1.getText() + "\n";
            message += "Результат:" + main.getResult() +"\n";
            JOptionPane.showMessageDialog(null, message, "Complete!", JOptionPane.PLAIN_MESSAGE);
            ImageIcon icon1 = new ImageIcon("src/heart.gif");
            JOptionPane.showMessageDialog(null, "Thanks for using my programe!<3",
                    "Customized Dialog", JOptionPane.INFORMATION_MESSAGE, icon1);
        }
    }
}