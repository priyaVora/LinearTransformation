package control

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

	public Matrix rX(double thetaValues) {
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

	public Matrix rY(double thetaValues) {
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

	public Matrix rZ(double thetaValues) {
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

	public Matrix scale_2D(double kx, double ky) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = kx;
		resultValue[0][1] = 0;

		resultValue[1][0] = 0;
		resultValue[1][1] = ky;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix scale_2D_Vectors(double k, double nx, double ny) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = (1 + ((k - 1) * (nx * nx)));
		resultValue[0][1] = (((k - 1) * (nx * ny)));

		resultValue[1][0] = (((k - 1) * (nx * ny)));
		resultValue[1][1] = (1 + ((k - 1) * (ny * ny)));

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix scale_3D(double kx, double ky, double kz) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[3][3];
		resultValue[0][0] = kx;
		resultValue[0][1] = 0;
		resultValue[0][2] = 0;

		resultValue[1][0] = 0;
		resultValue[1][1] = ky;
		resultValue[1][2] = 0;

		resultValue[2][0] = 0;
		resultValue[2][1] = 0;
		resultValue[2][2] = kz;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix scale_3D_Vector(double k, double nx, double ny, double nz) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[3][3];
		resultValue[0][0] = (1 + ((k - 1) * (nx * nx)));
		resultValue[0][1] = (((k - 1) * (nx * ny)));
		resultValue[0][2] = ((k - 1) * (nx * nz));

		resultValue[1][0] = ((k - 1) * (nx * ny));
		resultValue[1][1] = (1 + ((k - 1) * (ny * ny)));
		resultValue[1][2] = ((k - 1) * (ny * nz));

		resultValue[2][0] = ((k - 1) * (nx * nz));
		resultValue[2][1] = ((k - 1) * (ny * nz));

		resultValue[2][2] = (1 + ((k - 1) * (nz * nz)));

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix orthographicProjection_2D_Horizontal() {
		Matrix resultMatrix = new Matrix();
		double[][] p = new double[2][2];
		p[0][0] = 1;
		p[0][1] = 0;
		p[1][0] = 0;
		p[1][1] = 0;
		resultMatrix.setCurrentMatrix(p);
		return resultMatrix;
	}

	public Matrix orthographicProjection_2D_Vertical() {
		Matrix resultMatrix = new Matrix();
		double[][] p = new double[2][2];
		p[0][0] = 0;
		p[0][1] = 0;
		p[1][0] = 0;
		p[1][1] = 1;
		resultMatrix.setCurrentMatrix(p);
		return resultMatrix;
	}

	public Matrix orthographicProjection_3D(double nx, double ny, double nz) {
		Matrix resultMatrix = new Matrix();
		double[][] currentValues = new double[3][3];
		currentValues[0][0] = (1 - (nx * nx));
		currentValues[0][1] = (-nx * ny);
		currentValues[0][2] = (-nx * nz);

		currentValues[1][0] = (-nx * ny);
		currentValues[1][1] = (1 - (ny * ny));
		currentValues[1][2] = (-ny * nz);

		currentValues[2][0] = (-nx * nz);
		currentValues[2][1] = (-ny * nz);
		currentValues[2][2] = (1 - (nz * nz));

		resultMatrix.setCurrentMatrix(currentValues);
		return resultMatrix;
	}

	public Matrix reflection_Horizontal_2D() {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = 1;
		resultValue[0][1] = 0;

		resultValue[1][0] = 0;
		resultValue[1][1] = -1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix reflection_Vertical_2D() {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = -1;
		resultValue[0][1] = 0;

		resultValue[1][0] = 0;
		resultValue[1][1] = 1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix reflection_Vector(double nx, double ny) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = (1 - (2 * (nx * nx)));
		resultValue[0][1] = (-2 * nx * ny);

		resultValue[1][0] = (-2 * nx * ny);
		resultValue[1][1] = (1 - (2 * (ny * ny)));

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix reflection_3D(double nx, double ny, double nz) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[3][3];
		resultValue[0][0] = (1 - (2 * (nx * nx)));
		resultValue[0][1] = (-2 * nx * ny);
		resultValue[0][2] = (-2 * nx * nz);

		resultValue[1][0] = (-2 * nx * ny);
		resultValue[1][1] = (1 - (2 * (ny * ny)));
		resultValue[1][2] = (-2 * ny * nz);

		resultValue[2][0] = (-2 * nx * nz);
		resultValue[2][1] = (-2 * ny * nz);
		resultValue[2][2] = (1 - (2 * (nz * nz)));

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;

	}

	public Matrix shear_Horizontal(double k) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = 1;
		resultValue[0][1] = k;

		resultValue[1][0] = 0;
		resultValue[1][1] = 1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix shear_Vertical(double k) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = 1;
		resultValue[0][1] = 0;

		resultValue[1][0] = k;
		resultValue[1][1] = 1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix shear_3rdRow(double k1, double k2) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = 1;
		resultValue[0][1] = 0;
		resultValue[0][2] = 0;

		resultValue[1][0] = k1;
		resultValue[1][1] = 1;
		resultValue[1][2] = 0;

		resultValue[2][0] = k2;
		resultValue[2][1] = 0;
		resultValue[2][2] = 1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix shear_2ndRow(double k1, double k2) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = 1;
		resultValue[0][1] = k2;
		resultValue[0][2] = 0;

		resultValue[1][0] = 0;
		resultValue[1][1] = 1;
		resultValue[1][2] = 0;

		resultValue[2][0] = 0;
		resultValue[2][1] = k2;
		resultValue[2][2] = 1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix shear_1stRow(double k1, double k2) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[2][2];
		resultValue[0][0] = 1;
		resultValue[0][1] = 0;
		resultValue[0][2] = k1;

		resultValue[1][0] = 0;
		resultValue[1][1] = 1;
		resultValue[1][2] = k2;

		resultValue[2][0] = 0;
		resultValue[2][1] = 0;
		resultValue[2][2] = 1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix translation_2D(double xChange, double yChange) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[3][3];
		resultValue[0][0] = 1;
		resultValue[0][1] = 0;
		resultValue[0][2] = xChange;

		resultValue[1][0] = 0;
		resultValue[1][1] = 1;
		resultValue[1][2] = yChange;

		resultValue[2][0] = 0;
		resultValue[2][1] = 0;
		resultValue[2][2] = 1;

		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public Matrix translation_3D(double xChange, double yChange, double zChange) {
		Matrix resultMatrix = new Matrix();
		double[][] resultValue = new double[4][4];

		resultValue[0][1] = 1;
		resultValue[0][2] = 0;
		resultValue[0][3] = 0;
		resultValue[0][4] = xChange;

		resultValue[1][1] = 0;
		resultValue[1][2] = 1;
		resultValue[1][3] = 0;
		resultValue[1][4] = yChange;

		resultValue[2][0] = 0;
		resultValue[2][1] = 0;
		resultValue[2][2] = 1;
		resultValue[2][3] = zChange;

		resultValue[3][0] = 0;
		resultValue[3][1] = 0;
		resultValue[3][2] = 0;
		resultValue[3][3] = 1;
		resultMatrix.setCurrentMatrix(resultValue);
		return resultMatrix;
	}

	public DimensionType getDimension() {
		return dimension;
	}

	public void setDimension(DimensionType dimension) {
		this.dimension = dimension;
	}

}
