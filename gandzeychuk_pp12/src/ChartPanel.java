import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    private DataManager dataManager;

    public ChartPanel(DataManager dataManager) {
        this.dataManager = dataManager;
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        ArrayList<DataManager.Element> normalized = dataManager.getNormalized(getHeight() - 20);
        int width = 25;
        if (normalized.size() > 12)
            width = 12 * 25 / normalized.size();
        for (int i=0; i < normalized.size(); i++) {
            DataManager.Element e = normalized.get(i);
            g.fillRect((int) (i * width + 0.12 * width), getHeight() - e.value, (int) (width - 0.12 * width), e.value);
        }
    }
}
