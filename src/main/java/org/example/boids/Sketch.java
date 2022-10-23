package org.example.boids;

import processing.core.PApplet;

public class Sketch extends PApplet {
    public DeltaTime deltaTime;
    public Boid testBoid = new Boid();

    @Override
    public void settings() {
        size(1080, 720);
    }

    @Override
    public void setup() {
        deltaTime = new DeltaTime();
        testBoid.setPosition(new Vector2(200, 200));
        testBoid.setVelocity(new Vector2(10, 10));
    }

    public void update() {
        deltaTime.update();
        testBoid.update(deltaTime);
    }

    @Override
    public void draw() {
        update();
        background(255);
        testBoid.draw(this);
    }
}
