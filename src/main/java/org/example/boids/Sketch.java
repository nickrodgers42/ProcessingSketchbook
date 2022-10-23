package org.example.boids;

import java.util.ArrayList;

import processing.core.PApplet;

public class Sketch extends PApplet {
    private DeltaTime deltaTime = new DeltaTime();

    private ArrayList<Boid> boidList = new ArrayList<>();
    private int numBoids = 200;

    @Override
    public void settings() {
        size(980, 720);
    }

    @Override
    public void setup() {
        windowResize(980, 720);
        for (int i = 0; i < numBoids; ++i) {
            boidList.add(
                new Boid(new Vector2(this.width, this.height))
            );
        }
    }

    public void update() {
        double dt = deltaTime.update();
        for (Boid b : boidList) {
            b.update(dt, this, this.boidList);
        }
    }

    @Override
    public void draw() {
        update();
        background(60);
        for (Boid b : boidList) {
            b.draw(this);
        }
    }
}
