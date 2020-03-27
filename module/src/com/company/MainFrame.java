package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private ArrayList<String> groupName = new ArrayList<>();
    private ArrayList<Integer> studentsAmount = new ArrayList<>();

    MainFrame(String title) {
        super(title);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setVisible(true);

        GridBagConstraints frameConstraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Прикладне програмування");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JTable infoTable = new JTable();
        Object[] columns = {"Назва групи","Кількість студентів"};
        DefaultTableModel infoTableModel = new DefaultTableModel();
        infoTableModel.setColumnIdentifiers(columns);
        infoTable.setModel(infoTableModel);
        Font font = new Font("Times New Roman",Font.PLAIN,23);
        infoTable.setFont(font);
        infoTable.setRowHeight(30);

        JScrollPane infoTableScrollPane = new JScrollPane(infoTable);

        Object[] row = new Object[2];

        InfoLabel groupNameLabel = new InfoLabel("Назва групи");
        InfoLabel studentAmountLabel = new InfoLabel("Кількість студентів");

        Font textFieldFont = new Font("Times New Roman", Font.PLAIN, 18);
        JTextField groupNameField = new JTextField(15);
        groupNameField.setFont(textFieldFont);
        JTextField studentAmountField = new JTextField(15);
        studentAmountField.setFont(textFieldFont);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.fill = GridBagConstraints.BOTH;
        panelConstraints.gridx = 0;
        panelConstraints.insets = new Insets(10, 10, 10, 10);
        infoPanel.add(groupNameLabel, panelConstraints);
        infoPanel.add(studentAmountLabel, panelConstraints);
        panelConstraints.gridx = 1;
        panelConstraints.insets = new Insets(10, 5, 5, 40);
        infoPanel.add(groupNameField, panelConstraints);
        infoPanel.add(studentAmountField, panelConstraints);

        Font buttonFont = new Font ("Times New Roman", Font.BOLD, 20);
        JButton addButton = new JButton("Додати");
        addButton.setFont(buttonFont);
        addButton.addActionListener(e -> {
            try {
                row[0] = groupNameField.getText();
                row[1] = studentAmountField.getText();

                infoTableModel.addRow(row);
                studentsAmount.add(Integer.parseInt(studentAmountField.getText()));
                groupName.add(groupNameField.getText());

                groupNameField.setText("");
                studentAmountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Неправильний формат вводу даних!");
                setVisible(false);
            }
        });
        JButton editButton = new JButton("Редагувати");
        editButton.setFont(buttonFont);
        editButton.addActionListener(e -> {
            int i = infoTable.getSelectedRow();
            if (i >= 0) {
                infoTableModel.setValueAt(groupNameField.getText(), i, 0);
                groupName.remove(i);
                groupName.add(groupNameField.getText());
                infoTableModel.setValueAt(studentAmountField.getText(), i, 1);
                studentsAmount.remove(i);
                studentsAmount.add(Integer.parseInt(studentAmountField.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Помилка редагування!");
            }
        });
        JButton deleteButton = new JButton("Видалити");
        deleteButton.setFont(buttonFont);
        deleteButton.addActionListener(e -> {
            int i = infoTable.getSelectedRow();
            if (i >= 0) {
                infoTableModel.removeRow(i);
                studentsAmount.remove(i);
                groupName.remove(i);
            } else {
                JOptionPane.showMessageDialog(null, "Помилка видалення!");
            }
        });

        infoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = infoTable.getSelectedRow();

                groupNameField.setText(infoTableModel.getValueAt(i, 0).toString());
                studentAmountField.setText(infoTableModel.getValueAt(i, 1).toString());
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        JButton diagramButton = new JButton("ДІАГРАМА");
        diagramButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
        diagramButton.setBorder(new LineBorder(new Color(36, 116, 236), 2));
        diagramButton.setPreferredSize(new Dimension(200, 40));
        diagramButton.addActionListener(e -> {
            JFrame secondFrame = new JFrame();
            secondFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            secondFrame.setLayout(new BorderLayout());
            secondFrame.setLocationRelativeTo(null);
            secondFrame.setResizable(false);
            secondFrame.setVisible(true);

            JLabel secondFrameTitle = new JLabel("Діаграма кількості студентів");
            secondFrameTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
            secondFrameTitle.setHorizontalAlignment(JLabel.CENTER);

            Diagram diagram = new Diagram(groupName, studentsAmount);
            secondFrame.add(diagram);
            secondFrame.add(secondFrameTitle, BorderLayout.NORTH);
            secondFrame.setExtendedState(MAXIMIZED_BOTH);
        });

        frameConstraints.gridx = 0;
        add(titleLabel, frameConstraints);
        add(infoTableScrollPane, frameConstraints);
        add(infoPanel, frameConstraints);
        add(buttonPanel, frameConstraints);
        add(diagramButton, frameConstraints);
        pack();
    }
}
