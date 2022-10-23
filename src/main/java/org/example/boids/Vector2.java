package org.example.boids;

// Processing has its own vector class but I was interested in the math, so I
// wanted to give implementing this class a shot

import processing.core.PVector;

public class Vector2 {
    private double x;
    private double y;

    public Vector2() { x = 0; y = 0; }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        x = v.x;
        y = v.y;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double n) {
        this.x = n;
    }

    public void setY(double n) {
        this.y = n;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public double heading() {
        return Math.atan2(y, x);
    }

    public Vector2 mult(int n) {
        return new Vector2(x * n, y * n);
    }

    public Vector2 mult(double n) {
        return new Vector2(x * n, y * n);
    }

    public Vector2 div(int n) {
        return new Vector2(x / n, y / n);
    }

    public Vector2 div(double n) {
        return new Vector2(x / n, y / n);
    }

    public PVector toPVector() {
        return new PVector((float) x, (float) y);
    }
}
