
package model;

public class Vector {
	private String name = null;
	private Integer row = 0;
	private Integer column = 0;
	VectorOrientation vectorOrientation;
	double[][] currentVector = new double[0][0];

	public Vector(String name, Integer row, Integer column, VectorOrientation v) {
		this.name = name;
		this.row = row;
		this.column = column;
		createDefaultVector();
	}

	private void createDefaultVector() {

	}

	public Vector() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		if (vectorOrientation != null) {
			if (vectorOrientation == VectorOrientation.VERTICAL) {
				this.row = row;
			} else if (vectorOrientation == VectorOrientation.HORIZONTAL) {
				if (row > 1 || row < 1) {
					System.out.println(row + "can not be set for the row in a horizontal orientation.");
				} else {

				}
			}
		}

	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		if (vectorOrientation != null) {
			if (vectorOrientation == VectorOrientation.VERTICAL) {
				if (column > 1 || column < 1) {
					System.out.println(column + "can not be set for the column in a vertical orientation.");
				} else {

				}
			} else if (vectorOrientation == VectorOrientation.HORIZONTAL) {
				this.column = column;
			}
		}
	}

	public VectorOrientation getV() {
		return vectorOrientation;
	}

	public void setV(VectorOrientation v) {
		this.vectorOrientation = v;
	}

	public double[][] getCurrentVector() {
		return currentVector;
	}

	public void setCurrentVector(double[][] currentVector) {
		this.currentVector = currentVector;
	}

	@Override
	public String toString() {
		return "Vector: [" + name + "]: " + row + "X" + column + " " + vectorOrientation + " " + currentVector;
	}

	public VectorOrientation getVectorOrientation() {
		return vectorOrientation;
	}

	public void setVectorOrientation(VectorOrientation vectorOrientation) {
		this.vectorOrientation = vectorOrientation;
	}

	public void printVector() {
		System.out.println("\nPrint: [" + name + "] " + row + "X" + column + "\n");

		for (int i = 0; i < currentVector.length; i++) {
			for (int j = 0; j < currentVector[i].length; j++) {
				System.out.print(currentVector[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

}
