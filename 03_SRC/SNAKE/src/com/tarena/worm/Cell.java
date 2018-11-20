package com.tarena.worm;

public class Cell {
	private int row, col;

	public Cell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + "]";
	}

	@Override
	public int hashCode() {
		return row*1000+col;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Cell) {
			Cell c = (Cell) obj;
			return this.col==c.col && this.row==c.row;
		}
		return false;
	}
	
	
}
