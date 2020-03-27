package me.salmonmoses.sudoku.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SudokuModel {
	private int[][] grid;
	private int[][] errors;

	public SudokuModel() {
		this.grid = new int[9][9];
		this.errors = new int[9][9];
	}

	public void validate() {
		this.errors = new int[9][9];
		for(int i = 0; i < 9; ++i) {
			this.checkRow(i);
		}
		for(int i = 0; i < 9; ++i) {
			this.checkColumn(i);
		}
		for(int i = 0; i < 9; ++i) {
			this.checkSquare(i);
		}
	}

	public void checkRow(int rowIndex) {
		Set<Integer> alreadyWasThere = new HashSet<>();
		int[] row = this.grid[rowIndex];
		for (int i = 0; i < row.length; ++i) {
			if (alreadyWasThere.contains(row[i])) {
				for(int j = 0; j < errors[rowIndex].length; ++j) errors[rowIndex][j]++;
				return;
			} else {
				if (row[i] != 0) {
					alreadyWasThere.add(row[i]);
				}
			}
		}
	}

	public void checkColumn(int columnIndex) {
		Set<Integer> alreadyWasThere = new HashSet<>();
		for (int i = 0; i < grid.length; ++i) {
			if (alreadyWasThere.contains(grid[i][columnIndex])) {
				for (int j = 0; j < grid.length; ++j) {
					errors[j][columnIndex]++;
				}
				return;
			} else {
				if (grid[i][columnIndex] != 0) {
					alreadyWasThere.add(grid[i][columnIndex]);
				}
			}
		}
	}

	public void checkSquare(int i) {
		Set<Integer> alreadyWasThere = new HashSet<>();
		int squareStartX = i % 3 * 3;
		int squareFinishX = squareStartX + 3;
		int squareStartY = Math.floorDiv(i, 3) * 3;
		int squareFinishY = squareStartY + 3;
		for (int gridY = squareStartY; gridY < squareFinishY; ++gridY) {
			for (int gridX = squareStartX; gridX < squareFinishX; ++gridX) {
				if (alreadyWasThere.contains(grid[gridY][gridX])) {
					for (int errorY = squareStartY; errorY < squareFinishY; ++errorY) {
						for (int errorX = squareStartX; errorX < squareFinishX; ++errorX) {
							errors[errorY][errorX]++;
						}
					}
					return;
				} else {
					if (grid[gridY][gridX] != 0) {
						alreadyWasThere.add(grid[gridY][gridX]);
					}
				}
			}
		}
	}

	public boolean canAddToRow(int rowIndex, int numberToEnter) {
		int[] row = this.grid[rowIndex];
		for (int i = 0; i < row.length; ++i) {
			if (row[i] == numberToEnter) {
				return false;
			}
		}
		return true;
	}

	public boolean canAddToColumn(int columnIndex, int enteredNumber) {
		for (int i = 0; i < grid.length; ++i) {
			if (grid[i][columnIndex] == enteredNumber) {
				return false;
			}
		}
		return true;
	}

	public boolean canAddToSquare(int x, int y, int numberToEnter) {
		int squareStartX = Math.floorDiv(x, 3) * 3;
		int squareFinishX = squareStartX + 3;
		int squareStartY = Math.floorDiv(y, 3) * 3;
		int squareFinishY = squareStartY + 3;
		for (int gridY = squareStartY; gridY < squareFinishY; ++gridY) {
			for (int gridX = squareStartX; gridX < squareFinishX; ++gridX) {
				if (gridX != x && gridY != y && grid[gridX][gridY] == numberToEnter) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean hasErrors() {
		for (int[] row : grid) {
			for (int cell : row) {
				if (cell == 0) {
					return false;
				}
			}
		}
		for (int[] row : errors) {
			for (int cell : row) {
				if (cell > 0) {
					return false;
				}
			}
		}
		return true;
	}

	public int getValueAt(int x, int y) {
		return grid[y][x];
	}

	public void setValueAt(int x, int y, int value) {
		grid[y][x] = value;
	}

	public boolean isErrorAt(int x, int y) {
		return errors[y][x] > 0;
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public int[][] getErrors() {
		return errors;
	}

	public void setErrors(int[][] errors) {
		this.errors = errors;
	}
}
