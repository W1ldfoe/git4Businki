package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class modul1
{
    static int width = 1000;
    static int heigh = 600;
    static double columnWidth;
    static double columnHeigh;
    static int indentationLeft = width/40, indentationRight = width/40, indentationUp = width/20, indentationDown = width/20;

    static ArrayList<String> diagramElementRegion = new ArrayList<>();
    static ArrayList<Double> diagramElementArea = new ArrayList<>();
    static public void main(String []args)
    {
        getEnterDataToDiagram();
    }

    static JFrame getEnterDataToDiagram()
    {
        JFrame enter = new JFrame("modul1, Anton Turbo, PP-11, all rights are reserved");
        enter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        enter.setLayout(new GridBagLayout());
        enter.setSize(width/2, heigh/5);
        JLabel enterRegion = new JLabel("enter region:");
        enter.add(enterRegion, new GridBagConstraints(0,
                0,
                1,
                1,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0,
                0));
        JLabel enterArea = new JLabel("enter area:");
        enter.add(enterArea, new GridBagConstraints(1,
                0,
                1,
                1,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0,
                0));
        JTextField region = new JTextField();
        enter.add(region, new GridBagConstraints(0,
                1,
                1,
                1,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0,
                0));
        JTextField area = new JTextField();
        enter.add(area, new GridBagConstraints(1,
                1,
                1,
                1,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0,
                0));
        JButton add = new JButton("add");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    diagramElementArea.add(Double.parseDouble(area.getText()));
                    diagramElementRegion.add(region.getText());
                    enter.setVisible(false);
                    getEnterDataToDiagram();
                }
                catch (Exception exp)
                {
                    enter.setVisible(false);
                    getEnterDataToDiagram();
                }
            }
        });
        enter.add(add, new GridBagConstraints(0,
                2,
                1,
                1,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.LINE_END,
                new Insets(1, 1, 1, 1),
                0,
                0));
        JButton diagram = new JButton("diagram");
        diagram.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                enter.setVisible(false);
                try
                {
                    diagramElementArea.add(Double.parseDouble(area.getText()));
                    diagramElementRegion.add(region.getText());
                    JFrame diagram = getDiagram();
                    diagram.add(new MyComponent());
                }
                catch (Exception exp)
                {
                    JFrame diagram = getDiagram();
                    diagram.add(new MyComponent());
                }
            }
        });
        enter.add(diagram, new GridBagConstraints(1,
                2,
                1,
                1,
                1,
                1,
                GridBagConstraints.NORTH,
                GridBagConstraints.LINE_END,
                new Insets(1, 1, 1, 1),
                0,
                0));
        enter.setVisible(true);
        return enter;
    }

    static class MyComponent extends JComponent
    {
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D)g;
            g2.setFont(new Font("Bitstream Charter", 2, 20));
            for (int i = 0; i<diagramElementArea.size(); i++)
            {
                g2.setPaint(Color.gray);
                double d = (diagramElementArea.get(i));
                Rectangle2D r = new Rectangle2D.Double((indentationLeft+(columnWidth*i))+2,
                        heigh-indentationDown-(columnHeigh*d),
                        columnWidth-4,
                        columnHeigh*d);
                g2.fill(r);
                g2.setPaint(Color.black);
                g2.drawString(Double.toString(diagramElementArea.get(i)), (int)(indentationLeft+(columnWidth*i)+5), heigh - 70);
                g2.drawString(diagramElementRegion.get(i), (int)(indentationLeft+(columnWidth*i)+5), 20);
            }
        }
    }

    static JFrame getDiagram()
    {
        JFrame diagram = new JFrame("diagram");
        diagram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        diagram.setSize(width, heigh);
        columnWidth = (width-(indentationLeft+indentationRight))/(diagramElementRegion.size());
        columnHeigh = (heigh-(indentationDown+indentationUp))/getMaxArea();
        diagram.setVisible(true);
        diagram.add(new MyComponent());
        return diagram;
    }

    static double getMaxArea()
    {
        double max = 0;
        for (int i=0; i<diagramElementArea.size(); i++)
            if(diagramElementArea.get(i)>max)
                max = diagramElementArea.get(i);
        return max;
    }
}