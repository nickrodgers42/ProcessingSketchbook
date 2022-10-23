package org.example.boids;

abstract class Vehicle extends Mover {
    double maxSpeed = 4;
    double maxForce = 0.1;

    public Vector2 getSteeringForce(Vector2 target) {
        Vector2 desired = Vector2.sub(target, this.position);
        desired.normalize();
        desired.mult(this.maxSpeed);

        Vector2 steeringForce = Vector2.sub(desired, this.velocity);
        steeringForce.limit(this.maxForce);
        return steeringForce;
    }
}
