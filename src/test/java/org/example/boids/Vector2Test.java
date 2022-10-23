package org.example.boids;

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

    private static Stream<Arguments> headingProvider() {
        return Stream.of(
            Arguments.of(new Vector2(0, 0), 0),
            Arguments.of(new Vector2(1, 0), 0),
            Arguments.of(new Vector2(0, 1), Math.PI / 2),
            Arguments.of(new Vector2(-1, 0), Math.PI),
            Arguments.of(new Vector2(0, -1), (3 * Math.PI) / 2),
            Arguments.of(new Vector2(1, 1), Math.PI / 4));
    }

    @ParameterizedTest
    @MethodSource("headingProvider")
    public void testHeading(Vector2 vector, double expectedHeading) {
        assert Math.abs(vector.heading() - expectedHeading) < 0.001;
    }

    @Test
    public void testFromAngle() {
        Vector2 vector = Vector2.fromAngle(Math.PI);
        assert Math.abs(vector.getX() + 1) < 0.001;
        assert Math.abs(vector.getY()) < 0.001;
        vector = Vector2.fromAngle((3 * Math.PI) / 2);
        assert Math.abs(vector.getY() + 1) < 0.001;
        assert Math.abs(vector.getX()) < 0.001;
    }

}
