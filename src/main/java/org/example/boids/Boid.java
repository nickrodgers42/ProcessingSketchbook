package org.example.boids;

import processing.core.PApplet;

public class Boid {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public Boid() {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.acceleration = new Vector2();
    }

    public Boid(Vector2 position, Vector2 velocity, Vector2 acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void update(double deltaTime) {

    }

    public void drawBoid(PApplet sketch) {
        sketch.pushMatrix();
        sketch.translate((int)position.getX(), (int)position.getY());
        sketch.rotate((float) velocity.heading());
        sketch.fill(0, 255, 255);
        sketch.triangle(
            0, -10,
            -5, 5,
            5, 5);
        sketch.popMatrix();
    }

    public void drawPerceptionRadius(PApplet sketch) {
        sketch.pushMatrix();
        sketch.translate((int)position.getX(), (int)position.getY());
        sketch.arc(
            0, 0, 100, 100,
            (float)(3 * Math.PI / 4),
            (float)(Math.PI / 4 + 2 * Math.PI));
        sketch.popMatrix();
    }

    public void draw(PApplet sketch) {
        drawPerceptionRadius(sketch);
        drawBoid(sketch);
    }
}
