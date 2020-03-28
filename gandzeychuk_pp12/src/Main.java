import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        DataManager dataManager = new DataManager();

        ChartPanel chartPanel = new ChartPanel(dataManager);
        InterfacePanel interfacePanel = new InterfacePanel(dataManager, chartPanel);

        mainPanel.add(chartPanel);
        mainPanel.add(interfacePanel);

        jFrame.add(mainPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
