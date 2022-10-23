package org.example.boids;

// Processing has its own vector class but I was interested in the math, so I
// wanted to give implementing this class a shot

public class Vector2 {
    private double x;
    private double y;

    public Vector2() {}

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public void setX(double n) { this.x = n; }

    public void setY(double n) { this.y = n; }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }
}
