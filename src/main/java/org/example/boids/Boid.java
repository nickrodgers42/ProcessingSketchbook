package org.example.boids;

public class Boid {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public Boid() {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.acceleration = new Vector2();
    }
}
