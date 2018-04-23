package control;

import model.DimensionType;
import model.Matrix;

public class TransformationCalculator {
	private DimensionType dimension = null;

	public TransformationCalculator(DimensionType d) {
		dimension = d;
	}

	public boolean validDimension() {
		if (dimension != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Matrix rotation(double[][] currentMatrix) {
		if (validDimension() == true) {
			Matrix resultMatrix = new Matrix();

			return resultMatrix;
		} else {
			return null;
		}
	}

	public void rotationVectors() {

	}

	public void scale() {

	}

	public void orthographicProjection() {

	}

	public void reflection() {

	}

	public void shear() {

	}

	public void translation() {

	}

	public DimensionType getDimension() {
		return dimension;
	}

	public void setDimension(DimensionType dimension) {
		this.dimension = dimension;
	}

}
