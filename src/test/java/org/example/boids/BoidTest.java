package org.example.boids;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BoidTest {
    @Test
    public void testGetBoidsInView() {
        Boid testBoid = new Boid();
        testBoid.setVelocity(new Vector2(0, 1));
        ArrayList<Boid> boidList = new ArrayList<>();
        boidList.add(testBoid);
        Boid secondBoid = new Boid();
        boidList.add(secondBoid);

        assertEquals(testBoid.getVisibleBoids(boidList).size(), 0);

        secondBoid.setPosition(new Vector2(0, 101));
        assertEquals(testBoid.getVisibleBoids(boidList).size(), 0);

        secondBoid.setPosition(new Vector2(0, 1));
        assertEquals(testBoid.getVisibleBoids(boidList).size(), 1);

        secondBoid.setPosition(new Vector2(0, -1));
        assertEquals(testBoid.getVisibleBoids(boidList).size(), 0);

        secondBoid.setPosition(new Vector2(-Math.sqrt(2) / 2, -Math.sqrt(2) / 2));
        assertEquals(testBoid.getVisibleBoids(boidList).size(), 1);

        secondBoid.setPosition(new Vector2(-1 / 2, -Math.sqrt(3) / 2));
        assertEquals(testBoid.getVisibleBoids(boidList).size(), 0);

        secondBoid.setPosition(new Vector2(Math.sqrt(2) / 2, -Math.sqrt(2) / 2));
        assertEquals(testBoid.getVisibleBoids(boidList).size(), 1);

        secondBoid.setPosition(new Vector2(1 / 2, -Math.sqrt(3) / 2));
        assertEquals(testBoid.getVisibleBoids(boidList).size(), 0);
    }
}
