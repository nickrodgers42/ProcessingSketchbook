package org.example.boids;

abstract class Mover {
    Vector2 position = new Vector2();
    Vector2 velocity = new Vector2();
    Vector2 acceleration = new Vector2();
    double mass = 1;

    public Vector2 getPosition() {
        return new Vector2(this.position);
    }

    public void setPosition(Vector2 position) {
        this.position = new Vector2(position);
    }

    public Vector2 getVelocity() {
        return new Vector2(this.velocity);
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = new Vector2(velocity);
    }

    public Vector2 getAcceleration() {
        return new Vector2(this.acceleration);
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = new Vector2(acceleration);
    }

    public void applyForce(Vector2 force) {
        Vector2 appliedForce = Vector2.div(force, mass);
        this.acceleration.add(appliedForce);
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return this.mass;
    }
}
