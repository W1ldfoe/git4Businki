package me.salmonmoses.sudoku.views;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
	public MainMenuView() {
		super("Sudoku");
		Integer[] difficultyLevels = {16, 20, 32, 40, 55, 64, 0};
		JPanel content = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel welcome = new JLabel("Welcome to Sudoku!");
		JLabel tipsLabel = new JLabel("How many tips do you want to have?");
		JButton startBtn = new JButton("Start");
		JComboBox<Integer> diffLvlCombo = new JComboBox<Integer>(difficultyLevels);
		startBtn.addActionListener(e -> {
			int tipsAmount = difficultyLevels[diffLvlCombo.getSelectedIndex()];
			GameView game = new GameView(tipsAmount);
			game.setVisible(true);
		});

		content.add(welcome, constraints);
		constraints.gridy = 1;
		content.add(tipsLabel, constraints);
		constraints.gridx = 1;
		content.add(diffLvlCombo, constraints);
		constraints.gridy = 2;
		constraints.gridx = 1;
		content.add(startBtn, constraints);

		this.setContentPane(content);

		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
