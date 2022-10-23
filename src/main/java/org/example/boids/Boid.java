package org.example.boids;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Boid {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private double maxSpeed = 1;
    private double maxForce = 3;
    private boolean debug = false;
    private double viewingRadius = 100;
    private double viewingAngle = 3 * Math.PI / 4;
    private double desiredSeparation = 20;
    private boolean red = false;

    public void setRed(boolean b) {
        this.red = b;
    }

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

    public Boid(Vector2 position, Vector2 velocity, Vector2 acceleration, boolean debug) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.debug = debug;
    }

    public ArrayList<Boid> getBoidsInView(ArrayList<Boid> boidList) {
        ArrayList<Boid> inView = new ArrayList<>();
        for (Boid b : boidList) {
            b.setRed(false);
            if (b.getPosition().getX() == this.position.getX() &&
                    b.getPosition().getY() == this.position.getY()) {
                continue;
            }
            if (this.position.distance(b.getPosition()) > viewingRadius) {
                continue;
            }
            Vector2 target = Vector2.sub(b.position, this.velocity);
            if (Vector2.angleBetween(this.velocity, target) > viewingAngle) {
                continue;
            }
            inView.add(b);
            b.setRed(true);
        }
        return inView;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    // public void separate(ArrayList<Boid> boids) {
    //     for

    public void update(double deltaTime, PApplet sketch, ArrayList<Boid> boidList) {
        ArrayList<Boid> inView = getBoidsInView(boidList);

        if (this.debug) {
            System.out.println("There are " + inView.size() + " boids in view");
        }


        this.velocity.add(this.acceleration);
        this.velocity.limit(maxSpeed);
        this.position.add(this.velocity);
        if (this.position.getX() > sketch.width) {
            this.position.setX(0);
        }
        if (this.position.getY() > sketch.height) {
            this.position.setY(0);
        }
        if (this.position.getX() < 0) {
            this.position.setX(sketch.width);
        }
        if (this.position.getY() < 0) {
            this.position.setY(sketch.height);
        }
    }

    public void drawBoid(PApplet sketch) {
        sketch.pushMatrix();
        sketch.translate((int)position.getX(), (int)position.getY());
        sketch.rotate((float)(velocity.heading() + Math.PI / 2));
        sketch.fill(0, 255, 255);
        if (this.debug) {
            sketch.fill(255, 0, 0);
        }
        else if (this.red) {
            sketch.fill(255, 0, 0);
        }

        sketch.triangle(
            0, -10,
            -5, 5,
            5, 5);
        sketch.popMatrix();
    }

    public void drawPerceptionRadius(PApplet sketch) {
        sketch.pushMatrix();
        sketch.translate((int)position.getX(), (int)position.getY());
        sketch.rotate((float)(velocity.heading() + Math.PI / 2));
        sketch.noFill();
        sketch.arc(
            0, 0, (float)viewingRadius, (float)viewingRadius,
            (float)(3 * Math.PI / 4),
            (float)(Math.PI / 4 + 2 * Math.PI),
            PConstants.PIE);
        sketch.popMatrix();
    }

    public void draw(PApplet sketch) {
        if (this.debug) {
            drawPerceptionRadius(sketch);
        }
        drawBoid(sketch);
    }
}
