package me.salmonmoses.sudoku.views;

import me.salmonmoses.sudoku.controllers.CellController;
import me.salmonmoses.sudoku.ModelFactory;
import me.salmonmoses.sudoku.models.SudokuModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameView extends JFrame {
	private final JPanel gamePanel;

	public GameView(int tipsAmount) {
		super("Sudoku");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setSize(300, 300);
		this.setResizable(false);
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(3, 3));
		SudokuModel gameModel = ModelFactory.generateModel(tipsAmount);
		for (int i = 0; i < 9; ++i) {
			JPanel squarePanel = new JPanel(new GridLayout(3, 3));
			squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			for (int y = 0; y < 3; ++y) {
				for (int x = 0; x < 3; ++x) {
					int cellX = i % 3 * 3 + x;
					int cellY = Math.floorDiv(i, 3) * 3 + y;
					JLabel cell = createCell(gameModel, cellY, cellX);
					squarePanel.add(cell);
				}
			}
			gamePanel.add(squarePanel);
		}
		gamePanel.setBorder(new EmptyBorder(3, 2, 3, 2));
		this.add(gamePanel);
		JButton submitButton = new JButton("Submit");
		submitButton.setSize(100, 100);
		submitButton.addActionListener(e -> {
			if (gameModel.hasErrors()) {
				JOptionPane.showMessageDialog(null, "Well done! You won!");
			} else {
				JOptionPane.showMessageDialog(null, "Sorry, you should try better, there are still errors in your " +
						"grid!");
			}
		});
		this.add(submitButton);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JLabel createCell(SudokuModel gameModel, int y, int x) {
		CellController controller = new CellController(gameModel, this, x, y);
		int cellValue = gameModel.getValueAt(x, y);
		JLabel cell = new JLabel(cellValue != 0 ? String.valueOf(cellValue) : " ");
		cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		cell.setOpaque(true);
		cell.setHorizontalAlignment(SwingConstants.CENTER);
		cell.addMouseListener(controller);
		cell.setBorder(new LineBorder(Color.BLACK, 1));
		return cell;
	}

	public void updateWithModel(SudokuModel model) {
		for (int i = 0; i < 9; ++i) {
			JPanel square = (JPanel) this.gamePanel.getComponent(i);
			for (int j = 0; j < 9; ++j) {
				JLabel cellLabel = (JLabel) square.getComponent(j);
				int x = i % 3 * 3 + j % 3;
				int y = Math.floorDiv(i, 3) * 3 + Math.floorDiv(j, 3);
				int cellValue = model.getValueAt(x, y);
				cellLabel.setText(cellValue != 0 ? String.valueOf(cellValue) : " ");
				if (model.isErrorAt(x, y)) {
					cellLabel.setBackground(Color.RED);
				} else {
					cellLabel.setBackground(Color.WHITE);
				}
				cellLabel.invalidate();
			}
		}
	}
}
