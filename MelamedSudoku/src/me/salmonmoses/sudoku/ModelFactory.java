package me.salmonmoses.sudoku;

import me.salmonmoses.sudoku.models.SudokuModel;

import java.util.Random;

public class ModelFactory {
	public static SudokuModel emptyModel() {
		return new SudokuModel();
	}

	public static SudokuModel generateModel(int tipsAmount) {
		SudokuModel model = new SudokuModel();
		Random r = new Random();
		int valueToAdd = 1, n = 1;
		for (int y = 0; y < 9; y++) {
			valueToAdd = n;
			for (int x = 0; x < 9; x++) {
				if (valueToAdd <= 9) {
					model.setValueAt(x, y, valueToAdd);
					valueToAdd++;
				} else {
					valueToAdd = 1;
					model.setValueAt(x, y, valueToAdd);
					valueToAdd++;
				}
			}
			n = valueToAdd + 3;
			if (valueToAdd == 10) {
				n = 4;
			}
			if (n > 9) {
				n = (n % 9) + 1;
			}
		}
		for(int i = 0; i < 81 - tipsAmount; ++i) {
			int x = r.nextInt(9);
			int y = r.nextInt(9);
			if(model.getValueAt(x, y) == 0) {
				--i;
				continue;
			}
			model.setValueAt(x, y, 0);
		}
		return model;
	}
}
