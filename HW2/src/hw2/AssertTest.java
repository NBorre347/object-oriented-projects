package hw2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class AssertTest {

	@Test
	void testMinValue() {
		assertTrue(-7 == AssertExp1.minValue(new double[] { -7 }));
		assertTrue(-7 == AssertExp1.minValue(new double[] { 1, -4, -7, 7, 8, 11 }));
		assertTrue(-13 == AssertExp1.minValue(new double[] { -13, -4, -7, 7, 8, 11 }));
		assertTrue(-9 == AssertExp1.minValue(new double[] { 1, -4, -7, 7, 8, 11, -9 }));
	}
	
	@Test
	void testMinPosition() {
		assertTrue(0 == AssertExp1.minPosition(new double[] { -7 }));
		assertTrue(2 == AssertExp1.minPosition(new double[] { 1, -4, -7, 7, 8, 11 }));
		assertTrue(0 == AssertExp1.minPosition(new double[] { -13, -4, -7, 7, 8, 11 }));
		assertTrue(6 == AssertExp1.minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 }));
	}
	
	@Test
	void testNumUnique() {
		assertTrue(0 == AssertExp1.numUnique(new double[] { }));
		assertTrue(1 == AssertExp1.numUnique(new double[] { 11 }));
		assertTrue(1 == AssertExp1.numUnique(new double[] { 11, 11, 11, 11 }));
		assertTrue(8 == AssertExp1.numUnique(new double[] { 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }));
		assertTrue(8 == AssertExp1.numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 }));
	}
	
	@Test
	void testRemoveDuplicates() {
		assertTrue(Arrays.equals(new double[] { }, AssertExp1.removeDuplicates(new double[] { })));
		assertTrue(Arrays.equals(new double[] { 11 }, AssertExp1.removeDuplicates(new double[] { 11 })));
		assertTrue(Arrays.equals(new double[] { 11 }, AssertExp1.removeDuplicates(new double[] { 11, 11, 11, 11 })));
		assertTrue(Arrays.equals(new double[] { 11, 22, 33, 44, 55, 66, 77, 88 }, AssertExp1.removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 })));
		assertTrue(Arrays.equals(new double[] { 11, 22, 33, 44, 55, 66, 77, 88 }, AssertExp1.removeDuplicates(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })));
	}

}
