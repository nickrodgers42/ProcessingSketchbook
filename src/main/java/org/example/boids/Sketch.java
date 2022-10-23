package org.example.boids;

import processing.core.PApplet;

public class Sketch extends PApplet {
    private DeltaTime deltaTime = new DeltaTime();

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void draw() {
        double dt = deltaTime.update();
        background(255);
    }
}
