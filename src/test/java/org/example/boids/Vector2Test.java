package org.example.boids;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Vector2Test {
    @Test
    public void testDefaultConstructor() {
        Vector2 vector = new Vector2();
        assert vector.getX() == 0.0;
        assert vector.getY() == 0.0;
    }

    @Test
    public void testDoubleConstructor() {
        Vector2 vector = new Vector2(3.14, 1.61);
        assert vector.getX() == 3.14;
        assert vector.getY() == 1.61;
    }

    @Test
    public void testCopyConstructor() {
        Vector2 vectorA = new Vector2(3.14, 1.61);
        Vector2 vectorB = new Vector2(vectorA);
        vectorA.set(13, 21);
        assert vectorA.getX() == 13;
        assert vectorA.getY() == 21;
        assert vectorB.getX() == 3.14;
        assert vectorB.getY() == 1.61;
    }

    @Test
    public void testSetX() {
        Vector2 vector = new Vector2();
        assert vector.getX() == 0.0;
        vector.setX(5);
        assert vector.getX() == 5.0;
    }

    @Test
    public void testSetY() {
        Vector2 vector = new Vector2();
        assert vector.getY() == 0.0;
        vector.setY(5);
        assert vector.getY() == 5.0;
    }

    @Test
    public void testSet() {
        Vector2 vector = new Vector2();
        vector.set(3.14, 1.61);
        assert vector.getX() == 3.14;
        assert vector.getY() == 1.61;
    }

    @Test
    public void testEquals() {
        Vector2 vector = new Vector2();
        assertTrue(vector.equals(new Vector2()));
        vector.set(3.14, 1.61);
        assertTrue(vector.equals(new Vector2(3.14, 1.61)));
    }

    @Test
    public void testAddVector() {
        Vector2 vectorA = new Vector2(1, 2);
        Vector2 vectorB = new Vector2(3, 4);
        vectorA.add(vectorB);
        assert vectorA.getX() == 4;
        assert vectorA.getY() == 6;
    }

    @Test
    public void testAddComponents() {
        Vector2 vector = new Vector2(1, 2);
        vector.add(3, 4);
        assert vector.getX() == 4;
        assert vector.getY() == 6;
    }

    @Test
    public void testStaticAdd() {
        Vector2 vectorA = new Vector2(1, 2);
        Vector2 vectorB = new Vector2(3, 4);
        Vector2 vectorC = Vector2.add(vectorA, vectorB);
        assert vectorC.getX() == 4;
        assert vectorC.getY() == 6;
    }

    @Test
    public void testSubVector() {
        Vector2 vectorA = new Vector2(5, 10);
        Vector2 vectorB = new Vector2(3, 4);
        vectorA.sub(vectorB);
        Vector2 expected = new Vector2(2, 6);
        assertTrue(vectorA.equals(expected));
    }

    @Test
    public void testSubComponents() {
        Vector2 vector = new Vector2(5, 10);
        vector.sub(3, 4);
        Vector2 expected = new Vector2(2, 6);
        assertTrue(vector.equals(expected));
    }

    @Test
    public void testStaticSub() {
        Vector2 vectorA = new Vector2(5, 10);
        Vector2 vectorB = new Vector2(3, 4);
        Vector2 actual = Vector2.sub(vectorA, vectorB);
        Vector2 expected = new Vector2(2, 6);
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testMult() {
        Vector2 vector = new Vector2(3, 4);
        vector.mult(5);
        Vector2 expected = new Vector2(15, 20);
        assertTrue(vector.equals(expected));
    }

    @Test
    public void testStaticMult() {
        Vector2 vector = new Vector2(3, 4);
        Vector2 actual = Vector2.mult(vector, 5);
        Vector2 expected = new Vector2(15, 20);
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testToString() {
        Vector2 vector = new Vector2(3.14, 1.61);
        String expected = "(3.14, 1.61)";
        assertTrue(vector.toString().equals(expected));
    }

    private static Stream<Arguments> headingProvider() {
        return Stream.of(
            Arguments.of(new Vector2(0, 0), 0),
            Arguments.of(new Vector2(1, 0), 0),
            Arguments.of(new Vector2(0, 1), Math.PI / 2),
            Arguments.of(new Vector2(-1, 0), Math.PI),
            Arguments.of(new Vector2(0, -1), (3 * Math.PI) / 2.0 - 2 * Math.PI),
            Arguments.of(new Vector2(1, 1), Math.PI / 4));
    }

    @ParameterizedTest
    @MethodSource("headingProvider")
    public void testHeading(Vector2 vector, double expectedHeading) {
        System.out.println(vector.heading() + " " + expectedHeading);
        assert Math.abs(vector.heading() - expectedHeading) < 0.001;
    }

    @Test
    public void testFromAngle() {
        assertTrue(Vector2.fromAngle(0).equals(new Vector2(1, 0)));
        Vector2 vector = Vector2.fromAngle(Math.PI);
        assertTrue(approximatelyEqual(vector.getX(), -1, 0.001));
        assertTrue(approximatelyEqual(vector.getY(), 0, 0.001));
        vector = Vector2.fromAngle((3 * Math.PI) / 2);
        assertTrue(approximatelyEqual(vector.getY(), -1, 0.001));
        assertTrue(approximatelyEqual(vector.getX(), 0, 0.001));
    }

    @Test
    public void testAngleBetween() {
        Vector2 vectorA = new Vector2(1, 0);
        Vector2 vectorB = new Vector2(1, 1);
        assert(Math.abs(
            Vector2.angleBetween(vectorA, vectorB) - Math.PI / 4) < 0.001);
    }

    @Test
    public void testPosition() {
        Vector2 vectorA = new Vector2();
        Vector2 vectorB = new Vector2(1, 1);
        assertTrue(vectorA.distance(vectorB) == Math.sqrt(2));
        vectorB.set(-1, -1);
        assertTrue(vectorA.distance(vectorB) == Math.sqrt(2));
        vectorB.set(1, -1);
        assertTrue(vectorA.distance(vectorB) == Math.sqrt(2));
        vectorA.set(5, 10);
        vectorB.set(9, 3);
        assertTrue(vectorA.distance(vectorB) == Math.sqrt(65));
    }

    public boolean approximatelyEqual(double a, double b, double absDiff) {
        double diff = Math.abs(a - b);
        if (diff < absDiff) {
            return true;
        }
        diff = Math.abs(b - a);
        if (diff < absDiff) {
            return true;
        }
        return false;
    }
}
