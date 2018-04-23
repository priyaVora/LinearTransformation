package control;

import java.io.Console;
import java.util.ArrayList;

import model.DimensionType;
import model.Matrix;
import model.Vector;

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

	private double cosineTheta(double number) {
		return Math.cos(Math.toRadians(number));

	}

	private double sineTheta(double number) {
		return Math.sin(Math.toRadians(number));
	}

	public ArrayList<Matrix> rotation(double thetaValues) {
		if (validDimension() == true) {
			ArrayList<Matrix> resultMatrices = new ArrayList<Matrix>();

			if (dimension.equals(dimension.TWO_D)) {
				resultMatrices = rotation_2DHelper(thetaValues);
				Matrix nwOne = resultMatrices.get(0);
				nwOne.printMatrix();
			} else if (dimension.equals(dimension.THREE_D)) {
				resultMatrices = rotation3D_Helper(thetaValues);
				for (Matrix matrix : resultMatrices) {
					matrix.printMatrix();
				}
			}

			return resultMatrices;
		} else {
			return null;
		}
	}

	private ArrayList<Matrix> rotation_2DHelper(double thetaValues) {
		ArrayList<Matrix> resultMatrix = new ArrayList<Matrix>();
		Matrix r = new Matrix();
		double[][] currentValues_R = new double[2][2];
		currentValues_R[0][0] = cosineTheta(thetaValues);
		currentValues_R[0][1] = (-1 * sineTheta(thetaValues));
		currentValues_R[1][0] = sineTheta(thetaValues);
		currentValues_R[1][1] = cosineTheta(thetaValues);
		r.setCurrentMatrix(currentValues_R);
		resultMatrix.add(r);
		return resultMatrix;
	}

	private ArrayList<Matrix> rotation3D_Helper(double thetaValues) {
		ArrayList<Matrix> resultMatrix = new ArrayList<Matrix>();

		resultMatrix.add(0, rX(thetaValues));
		resultMatrix.add(1, rY(thetaValues));
		resultMatrix.add(2, rZ(thetaValues));
		return resultMatrix;

	}

	private Matrix rX(double thetaValues) {
		Matrix x = new Matrix();
		double[][] currentX = new double[3][3];
		currentX[0][0] = 1;
		currentX[0][1] = 0;
		currentX[0][2] = 0;

		currentX[1][0] = 0;
		currentX[1][1] = cosineTheta(thetaValues);
		currentX[1][2] = (-1 * sineTheta(thetaValues));

		currentX[2][0] = 0;
		currentX[2][1] = sineTheta(thetaValues);
		currentX[2][2] = cosineTheta(thetaValues);
		x.setCurrentMatrix(currentX);
		return x;
	}

	private Matrix rY(double thetaValues) {
		Matrix y = new Matrix();
		double[][] currentY = new double[3][3];
		currentY[0][0] = cosineTheta(thetaValues);
		currentY[0][1] = 0;
		currentY[0][2] = sineTheta(thetaValues);

		currentY[1][0] = 0;
		currentY[1][1] = 1;
		currentY[1][2] = 0;

		currentY[2][0] = (-1 * sineTheta(thetaValues));
		currentY[2][1] = 0;
		currentY[2][2] = cosineTheta(thetaValues);
		y.setCurrentMatrix(currentY);
		return y;
	}

	private Matrix rZ(double thetaValues) {
		Matrix z = new Matrix();
		double[][] currentZ = new double[3][3];
		currentZ[0][0] = cosineTheta(thetaValues);
		currentZ[0][1] = (-1 * sineTheta(thetaValues));
		currentZ[0][2] = 0;

		currentZ[1][0] = sineTheta(thetaValues);
		currentZ[1][1] = cosineTheta(thetaValues);
		currentZ[1][2] = 0;

		currentZ[2][0] = 0;
		currentZ[2][1] = 0;
		currentZ[2][2] = 1;
		z.setCurrentMatrix(currentZ);
		return z;
	}

	public Matrix rotationVectors(double nx, double ny, double nz, double thetaValue) {
		Matrix resultMatrix = new Matrix();
		double[][] values = new double[3][3];
		values[0][0] = (((nx * nx) * (1 - cosineTheta(thetaValue)) + cosineTheta(thetaValue)));
		values[0][1] = ((nx * ny) * (1 - cosineTheta(thetaValue) - (nz * sineTheta(thetaValue))));
		values[0][2] = ((ny * nz) * (1 - cosineTheta(thetaValue) + (ny * sineTheta(thetaValue))));

		values[1][0] = ((nx * ny) * (1 - cosineTheta(thetaValue) + (nz * sineTheta(thetaValue))));
		values[1][1] = ((ny * ny) * (1 - cosineTheta(thetaValue) + cosineTheta(thetaValue)));
		values[1][2] = ((ny * nz) * (1 - cosineTheta(thetaValue) - (nx * sineTheta(thetaValue))));

		values[2][0] = ((nx * nz) * (1 - cosineTheta(thetaValue) - (ny * sineTheta(thetaValue))));
		values[2][1] = ((ny * nz) * (1 - cosineTheta(thetaValue) + (nx * sineTheta(thetaValue))));
		values[2][2] = (((nz * nz) * (1 - cosineTheta(thetaValue) + cosineTheta(thetaValue))));
		resultMatrix.setCurrentMatrix(values);
		return resultMatrix;
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
