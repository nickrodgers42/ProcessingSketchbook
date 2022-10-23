package org.example.boids;

import java.util.ArrayList;

import processing.core.PApplet;

public class Sketch extends PApplet {
    private DeltaTime deltaTime = new DeltaTime();

    private Boid testBoid;
    private ArrayList<Boid> boidList = new ArrayList<>();
    private int numBoids = 200;

    @Override
    public void settings() {
        size(980, 720);
    }

    @Override
    public void setup() {
        windowResize(980, 720);
        testBoid = new Boid(
            new Vector2(this.width / 2, this.height / 2),
            new Vector2(Math.random() * 8 - 4, Math.random() * 8 - 4),
            new Vector2());

        for (int i = 0; i < numBoids; ++i) {
            boidList.add(
                new Boid(
                    new Vector2(Math.random() * this.width, Math.random() * this.height),
                    new Vector2(Math.random() * 8 - 4, Math.random() * 8 - 4),
                    new Vector2()
            ));
        }
    }

    public void update() {
        double dt = deltaTime.update();
        testBoid.update(dt, this, this.boidList);
        for (Boid b : boidList) {
            b.update(dt, this, this.boidList);
        }
    }

    @Override
    public void draw() {
        update();
        background(255);
        testBoid.draw(this);
        for (Boid b : boidList) {
            b.draw(this);
        }
    }
}
