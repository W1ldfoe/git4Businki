package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame  {

    //            Declaration of the components_________________________________________________________________

    JLayeredPane container = getLayeredPane();
    JLabel region, amountOfAccidents, limitAccidents, minAccidents;
    JLabel stringDiagrams;
    JTextField regionInput, accidentsInput;
    JButton addChartButton;
    LineOfDiagram myDiagram = new LineOfDiagram();
    ElementOfDiagram element;
    int distance = 0, amountOfColumns = 0;
    public GUI(){
        super("Amount of accidents in Ukraine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //            Initialization of the previous declarations_________________________________________________________________

        addChartButton = new JButton("Add diagram");

        region = new JLabel("Region: ");
        amountOfAccidents = new JLabel("Amount of accidents:");
        stringDiagrams = new JLabel("Regions");
        limitAccidents = new JLabel("800");
        minAccidents = new JLabel("0");

        regionInput = new JTextField();
        accidentsInput = new JTextField();

        stringDiagrams.setFont(new Font("Romain",Font.ITALIC,8));

        //            Customize Button_________________________________________________________________

        addChartButton.addActionListener(new ButtonEventListener());
        addChartButton.setBackground(Color.black);
        addChartButton.setForeground(Color.WHITE);
        addChartButton.setFont(new Font("Arial", Font.PLAIN, 15));

        //            Positioning Components_________________________________________________________________

        minAccidents.setBounds(20,415,20,20);
        limitAccidents.setBounds(15,20,20,20);
        myDiagram.setBounds(25,0,1000,1000);
        region.setBounds(25,525,150,25);
        regionInput.setBounds(25,560,200,25);
        amountOfAccidents.setBounds(250,525,200,25);
        accidentsInput.setBounds(250,560,200,25);
        addChartButton.setBounds(500,550,150,50);
        stringDiagrams.setBounds(600,407,100,25);

//            Adding Components on the pane_________________________________________________________________

        container.add(region);
        container.add(amountOfAccidents);
        container.add(regionInput);
        container.add(accidentsInput);
        container.add(addChartButton);
        container.add(stringDiagrams);
        container.add(myDiagram);
        container.add(limitAccidents);
        container.add(minAccidents);

        setSize(700,700);
    }

    //            Drawing Graphic_________________________________________________________________

    public class LineOfDiagram extends JComponent{
        @Override
        public void paintComponent(Graphics figure) {
            figure.drawLine(25,25,25,425);
            figure.drawLine(25,425,625,425);
        }
    }

    //            Drawing Rectangles_________________________________________________________________

    public class ElementOfDiagram extends JComponent{
        @Override
        public void paintComponent(Graphics figure){
            super.paintComponent(figure);
            figure.setColor(Color.BLUE);
            figure.fillRect(25, (425 - (Integer.parseInt(accidentsInput.getText()) / 2)),25, (Integer.parseInt(accidentsInput.getText()) / 2 ));
            figure.setColor(Color.black);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(25, Integer.parseInt(accidentsInput.getText()));
        }

    }
    //            Action Listener for the button_________________________________________________________________

    class ButtonEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

//            Error handling_________________________________________________________________

            try{
                int integerValidation = Integer.parseInt(accidentsInput.getText());
            } catch (NumberFormatException errorException) {
                String error_message = "";
                error_message += "Error!\n" + "Incorrect input.";
                JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
                accidentsInput.setText(null);
                regionInput.setText(null);
            }
             if(Integer.parseInt(accidentsInput.getText()) > 800 || regionInput.getText().matches("[0-9]*$") || Integer.parseInt(accidentsInput.getText()) < 0 ){
                String message = "";
                message += "Error input!\n";
                message += "Your accidents: " + accidentsInput.getText() + "/800.";
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                accidentsInput.setText(null);
                regionInput.setText(null);
            } else {
                if (amountOfColumns==6){
                    accidentsInput.setText(null);
                    regionInput.setText(null);
                }
                else {

                    //            Drawing Diagram Items_________________________________________________________________

                    element = new ElementOfDiagram();
                    JLabel stringDiagrams = new JLabel(regionInput.getText());
                    JLabel stringAmountAccidents = new JLabel(accidentsInput.getText());
                    stringDiagrams.setFont(new Font("Arial", Font.BOLD,12));
                    distance = distance + 80;
                    stringAmountAccidents.setBounds(15, (415 - (Integer.parseInt(accidentsInput.getText()) / 2)), 20,20);
                    stringDiagrams.setBounds(distance + 20, 425,50,50);
                    element.setBounds(distance , 0, 50, 1000);
                    container.add(stringAmountAccidents);
                    container.add(stringDiagrams);
                    container.add(element);
                    amountOfColumns++;
                }
            }
        }
    }
}



