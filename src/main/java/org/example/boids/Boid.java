package org.example.boids;

import processing.core.PApplet;
import processing.core.PConstants;

public class Boid {
    private double perceptionAngle = (Math.PI * 4 ) / 3;
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 acceleration = new Vector2();

    public Boid() {}

    public Boid(Vector2 position, Vector2 velocity, Vector2 acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Vector2 getPosition() {
        return position;
    }


    public void setPosition(Vector2 position) {
        this.position = position;
    }


    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void update(DeltaTime dt) {}

    public void drawBoid(PApplet sketch) {
        sketch.stroke(0);
        sketch.strokeWeight(2);
        sketch.fill(0, 255, 255);
        sketch.pushMatrix();
        sketch.translate(
            (float) this.position.getX(),
            (float) this.position.getY());
        sketch.rotate((float) this.velocity.heading());
        sketch.triangle(
            0, -20,
            -10, 10,
            10, 10);
        sketch.popMatrix();
    }
    public void drawPerceptionArea(PApplet sketch) {
        sketch.pushMatrix();
        sketch.translate(
            (float) this.position.getX(),
            (float) this.position.getY());
        sketch.rotate((float) this.velocity.heading());
        sketch.arc(0, 0, 120, 120,
            PConstants.PI,
            PConstants.PI + PConstants.QUARTER_PI);
        sketch.popMatrix();
    }

    public void draw(PApplet sketch) {
        drawPerceptionArea(sketch);
        drawBoid(sketch);
    }
}
