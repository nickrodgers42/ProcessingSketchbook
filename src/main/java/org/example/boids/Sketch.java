package org.example.boids;

import processing.core.PApplet;

public class Sketch extends PApplet {
    private DeltaTime deltaTime = new DeltaTime();

    private Boid testBoid;

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        testBoid = new Boid(
            new Vector2(this.width / 2, this.height / 2),
            new Vector2(0, 0),
            new Vector2(0, 0));
    }

    public void update() {
        double dt = deltaTime.update();
        testBoid.update(dt);
    }

    @Override
    public void draw() {
        update();
        background(255);
        testBoid.draw(this);
    }
}
