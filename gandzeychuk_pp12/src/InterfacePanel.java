import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacePanel extends JPanel {
    private DataManager dataManager;
    private ChartPanel chartPanel;
    private JTextField tfName, tfValue;
    private JButton btAdd;

    public InterfacePanel(DataManager dataManager, ChartPanel chartPanel) {
        this.dataManager = dataManager;
        this.chartPanel = chartPanel;
        setPreferredSize(new Dimension(150, 75));
        setLayout(new GridLayout(3, 1));
        tfName = new JTextField();
        tfValue = new JTextField();
        btAdd = new JButton("ADD");

        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int value;
                try {
                    value = Integer.parseInt(tfValue.getText());
                    dataManager.elements.add(new DataManager.Element(tfName.getText(), value));
                    chartPanel.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        add(tfName);
        add(tfValue);
        add(btAdd);
    }
}
