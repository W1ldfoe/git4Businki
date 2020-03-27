package me.salmonmoses.sudoku.controllers;

import me.salmonmoses.sudoku.models.SudokuModel;
import me.salmonmoses.sudoku.views.GameView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellController extends MouseAdapter {
	private SudokuModel model;
	private GameView view;
	private int x;
	private int y;

	public CellController(SudokuModel model, GameView view, int x, int y) {
		this.model = model;
		this.view = view;
		this.x = x;
		this.y = y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int newValue = 0;
		do {
			String input = JOptionPane.showInputDialog("Enter number for this cell", model.getGrid()[y][x]);
			if (input == null) {
				return;
			}
			if(input.equals("")) {
				input = "0";
			}
			try {
				newValue = Integer.parseInt(input);
			} catch (NumberFormatException ex) {
				newValue = -2;
			}
		} while (newValue < 0 || newValue > 9);
		model.getGrid()[y][x] = newValue;
		model.validate();
		view.updateWithModel(model);
	}

	public SudokuModel getModel() {
		return model;
	}

	public void setModel(SudokuModel model) {
		this.model = model;
	}

	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
