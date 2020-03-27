//Решетник Денис ПП12
//Варіант 8
// На лекції неходив тому що вони співпадають з курсами
// (записався на курси по джс)
// курси реальні, на гите можете подивитись мій репозиторій FrontEnd

package com.company;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        String brand[] = new String[5], model[][] = new String[5][5];
        final int[] brandCounter = {0};
        Double quantity[][] = new Double[5][5];
        int modelCounter=0;
        final boolean[] newBrand = {true};
        final int[] modelCount = { 0,0,0,0,0 };

        JFrame f=new JFrame();//creating instance of JFrame
        JLabel label1 = new JLabel("Brand");
        JTextField textField1 = new JTextField();
        JLabel label2 = new JLabel("Model");
        JTextField textField2 = new JTextField();
        JLabel label3 = new JLabel("Quantity");
        JTextField textField3 = new JTextField();
        JButton b=new JButton("Add");
        JButton build=new JButton("Build Graph");
        JButton help = new JButton("help");
        JLabel action = new JLabel("");

        b.setBounds(120,140,100, 40);//x axis, y axis, width, height
        build.setBounds(120,180,100, 40);
        help.setBounds(120,220,100, 40);
        action.setBounds(120,260,500, 150);
        label1.setBounds(20,20,100, 40);
        textField1.setBounds(120,20,100, 40);
        label2.setBounds(20,60,100, 40);
        textField2.setBounds(120,60,100, 40);
        label3.setBounds(20,100,100, 40);
        textField3.setBounds(120,100,100, 40);

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (!textField1.equals("") && !textField2.equals("") && !textField3.equals("") && brandCounter[0]!=5) { //check if strings aren't empty
                    newBrand[0] = true;
                    for (int i = 0; i < 5; i++) {
                        if (brand[i] != null) {
                            if(model[i][4]==null) {
                                if (brand[i].equals(textField1.getText())) { // Actions if it's not new brand of the car
                                    model[i][modelCount[i]] = textField2.getText(); // Add model of the car
                                    quantity[i][modelCount[i]] = Double.parseDouble(textField3.getText()); // add quantity of the car
                                    modelCount[i]++;
                                    newBrand[0] = false;
                                }
                            }else{
                                textField1.setText("");
                                textField2.setText("");
                                textField3.setText("");
                                action.setText("Error!");
                                newBrand[0] = false;
                            }
                        }
                    }
                    if (newBrand[0] == true) { // Actions if it's new brand of the car
                        brand[brandCounter[0]] = textField1.getText(); // add new brand of the car
                        model[brandCounter[0]][modelCount[brandCounter[0]]] = textField2.getText(); // Add model of the car
                        quantity[brandCounter[0]][modelCount[brandCounter[0]]] = Double.parseDouble(textField3.getText()); // add quantity of the car
                        modelCount[brandCounter[0]]++;
                        brandCounter[0]++;
                        newBrand[0] = true;
                    }
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    action.setText("Success!");
                }
                else{
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    action.setText("Error!");
                }
            }
        });

        build.addActionListener(new ActionListener() { // build button listener
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame(); // create new frame and pass the database to another class
                    frame.getContentPane().add(new MyComponent(brand,model,quantity));
                frame.setBounds(50,50,1200,500);
                frame.setVisible(true);
            }
        });
        help.addActionListener(new ActionListener() { // help button listener
            public void actionPerformed(ActionEvent e) {
                action.setText("<html>You can type a 5 brands,<br> 5 models and their quantities.<br> The graphs will show you<br> stats on each brand. To<br> see graphs press build.<br> You don't need to restart<br> app to do the build again</html>");
            }
        });

        f.add(label1); //adding components to frame
        f.add(textField1);
        f.add(label2);
        f.add(textField2);
        f.add(label3);
        f.add(textField3);
        f.add(b);
        f.add(build);
        f.add(help);
        f.add(action);
        f.setBounds(800,350,350,450);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

    }
}

class Slice {
    double value;
    Color color;

    public Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}

class MyComponent extends JComponent {

    private final String[] brand;
    private final String[][] model;
    private final Double[][] quantity;

    Slice[] slices = { new Slice(0, Color.PINK), new Slice(0, Color.green),
            new Slice(0, Color.yellow), new Slice(0, Color.red) , new Slice(0, Color.MAGENTA) };
    Color[] colors = new Color[]{Color.red,Color.PINK,Color.yellow,Color.green,Color.magenta}; // creating slices for piechart and adding colors

    MyComponent(String[] brand, String[][] model, Double[][] quantity) { //constructor
        this.quantity = quantity;
       this.brand = brand;
       this.model = model;
    }

    public void paint(Graphics g) {
        for(int i=0, x=50, h=0;i<5;i++, x+=200, h+=200) { //painting 5 graphs
            if(brand[i]!=null) {
                Graphics2D g2 = (Graphics2D) g;
                Rectangle ar = new Rectangle();
                ar.setFrame(x, 50, 150, 150); //setting area for one graph

                for(int j=0;j<5;j++) {
                    slices[j]=new Slice(0,colors[j]);
                    if (quantity[i][j] != null) {
                        slices[j]=new Slice(quantity[i][j],colors[j]); //creating slice
                    }
                }
                drawPie((Graphics2D) g, ar, slices,i,x); //drawing
            }
        }
    }

    void drawPie(Graphics2D g, Rectangle area, Slice[] slices, int counter,int x) {
        double total = 0.0D;
        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }

        double curValue = 0.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.length; i++) { // drawing circle
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].value * 360 / total);

            g.setColor(slices[i].color);
            g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
            curValue += slices[i].value;
        }
        g.setColor(Color.black);
        g.drawString(brand[counter],x+70,240); // drawing name of the brand
        for(int j=0, modelPos=260;j<5;j++, modelPos+=20) {
            if (model[counter][j] != null) {
                g.setColor(colors[j]);
                g.drawString(model[counter][j],x+50,modelPos); // drawing names of the models
            }
        }
    }

}




