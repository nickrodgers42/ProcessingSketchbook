package org.example.boids;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Boid extends Vehicle {
    private double maxSpeed = 4;
    private double viewingRadius = 50;
    private double viewingAngle = Math.PI;
    private double desiredSeparation = 30;
    private boolean debug = false;
    private boolean red = false;
    private ArrayList<Boid> visibleBoids = new ArrayList<>();

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
            if (b.getPosition().equals(this.position)) {
                continue;
            }
            if (this.position.distance(b.getPosition()) > viewingRadius) {
                continue;
            }
            Vector2 target = Vector2.sub(b.getPosition(), this.position);
            if (Math.abs(Vector2.angleBetween(this.velocity, target)) > viewingAngle) {
                continue;
            }
            inView.add(b);
            if (this.debug) {
                b.setRed(true);
            }
        }
        return inView;
    }

    public Vector2 separate(ArrayList<Boid> inView) {
        Vector2 sum = new Vector2();
        int count = 0;
        for (Boid b : inView) {
            double distance = b.getPosition().distance(this.position);
            if (distance > desiredSeparation) {
                continue;
            }
            Vector2 desiredSteering = Vector2.sub(this.position, b.getPosition());
            desiredSteering.normalize();
            desiredSteering.div(distance);
            sum.add(desiredSteering);
            count++;
        }
        if (count > 0) {
            sum.div(count);
            sum.normalize();
            sum.mult(maxSpeed);
            Vector2 steeringForce = Vector2.sub(sum, this.velocity);
            steeringForce.limit(this.maxForce);
            return steeringForce;
        }
        return new Vector2();
    }

    public Vector2 align(ArrayList<Boid> inView) {
        Vector2 sum = new Vector2();
        for (Boid b : inView) {
            sum.add(b.getVelocity());
        }
        if (inView.size() > 0) {
            sum.div(inView.size());
            sum.normalize();
            sum.mult(this.maxSpeed);
            Vector2 steering = Vector2.sub(sum, velocity);
            steering.limit(this.maxForce);
            return steering;
        }
        return new Vector2();
    }

    public Vector2 cohesion(ArrayList<Boid> inView) {
        Vector2 sum = new Vector2();
        for (Boid b : inView) {
            sum.add(b.getPosition());
        }
        if (inView.size() > 0) {
            sum.div(inView.size());
            return getSteeringForce(sum);
        }
        return new Vector2();
    }

    public Vector2 avoidWalls(Vector2 sketchSize) {
        Vector2 desired = new Vector2(this.velocity);
        if (this.position.getX() < 25) {
            desired.setX(maxSpeed);
        }
        if (this.position.getX() > sketchSize.getX() - 25) {
            desired.setX(maxSpeed * -1);
        }
        if (this.position.getY() < 25) {
            desired.setY(maxSpeed);
        }
        if (this.position.getY() > sketchSize.getY() - 25) {
            desired.setY(maxSpeed * -1);
        }
        desired.normalize();
        desired.mult(maxSpeed);
        Vector2 steeringForce = Vector2.sub(desired, this.velocity);
        return steeringForce;
    }

    public void wrapCanvas(PApplet sketch) {
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

    public void update(double deltaTime, PApplet sketch, ArrayList<Boid> boidList) {
        this.visibleBoids = getBoidsInView(boidList);

        if (this.debug) {
            System.out.println("There are " + this.visibleBoids.size() + " boids in view");
        }

        // this.applyForce(avoidWalls(new Vector2(sketch.width, sketch.height)));
        this.applyForce(separate(this.visibleBoids));
        this.applyForce(align(this.visibleBoids));
        this.applyForce(cohesion(this.visibleBoids));

        this.velocity.add(this.acceleration);
        this.velocity.limit(maxSpeed);
        this.position.add(this.velocity);
        wrapCanvas(sketch);
        this.acceleration.mult(0);
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
        sketch.ellipseMode(PConstants.RADIUS);
        sketch.translate((int)position.getX(), (int)position.getY());
        sketch.rotate((float)(velocity.heading()));
        sketch.noFill();
        sketch.arc(
            0, 0,
            (float)viewingRadius, (float)viewingRadius,
            (float)-this.viewingAngle, (float)this.viewingAngle,
            PConstants.PIE);
        sketch.popMatrix();
    }

    public void drawLinesToVisibleBoids(PApplet sketch) {
        for (Boid b : this.visibleBoids) {
            sketch.line(
                (float)this.position.getX(), (float)this.position.getY(),
                (float)b.getPosition().getX(), (float)b.getPosition().getY());
        }
    }

    public void draw(PApplet sketch) {
        if (this.debug) {
            drawPerceptionRadius(sketch);
            drawLinesToVisibleBoids(sketch);
        }
        drawBoid(sketch);
    }
}
