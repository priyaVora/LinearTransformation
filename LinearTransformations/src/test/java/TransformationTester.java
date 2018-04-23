import static org.junit.Assert.*;

import org.junit.Test;

import control.TransformationCalculator;
import model.DimensionType;

public class TransformationTester {

	@Test
	public void rotation2DTest() {
		TransformationCalculator cal = new TransformationCalculator(DimensionType.TWO_D);
		cal.rotation(36);
	}

	@Test
	public void rotation3DTest() {
		TransformationCalculator cal = new TransformationCalculator(DimensionType.THREE_D);
		cal.rotation(338);
	}

}
