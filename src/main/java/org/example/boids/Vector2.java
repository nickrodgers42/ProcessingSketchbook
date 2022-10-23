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

    public void sub(Vector2 v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    public void sub(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public static Vector2 sub(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    public void mult(double n) {
        this.x *= n;
        this.y *= n;
    }

    public static Vector2 mult(Vector2 v, double n) {
        return new Vector2(v.getX() * n, v.getY() * n);
    }

    public boolean equals(Vector2 v) {
        return (this.x == v.getX() && this.y == v.getY());
    }


    public void div(double n) {
        this.x /= n;
        this.y /= n;
    }

    public static Vector2 div(Vector2 v, double n) {
        return new Vector2(
            v.getX() / n,
            v.getY() / n);
    }

    public double heading() {
        double theta = Math.atan2(this.y, this.x);
        return theta;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public static Vector2 fromAngle(double angle) {
        return new Vector2(Math.cos(angle), Math.sin(angle));
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void normalize() {
        double magnitude = this.mag();
        if (magnitude != 0) {
            this.div(magnitude);
        }
    }

    public void limit(double n) {
        double magnitude = this.mag();
        if (magnitude > n) {
            this.normalize();
            this.mult(n);
        }
    }

    public double distance(Vector2 v) {
        return Math.sqrt(
            (this.x - v.getX()) * (this.x - v.getX()) +
            (this.y - v.getY()) * (this.y - v.getY()));
    }

    public static double distance(Vector2 v1, Vector2 v2) {
        double x = v2.getX() - v1.getX();
        double y = v2.getY() - v1.getY();
        return Math.sqrt(x * x + y * y);
    }

    public double dot(Vector2 v) {
        return this.x * v.getX() + this.y * v.getY();
    }

    public static double dot(Vector2 v1, Vector2 v2) {
        return v1.getX() * v2.getX()  + v1.getY() * v2.getY();
    }

    public static double angleBetween(Vector2 v1, Vector2 v2) {
        if (v1.getX() == 0 && v1.getY() == 0) {
            return 0;
        }
        if (v2.getX() == 0 && v2.getY() == 0) {
            return 0;
        }
        double dotVal = Vector2.dot(v1, v2);
        double angle = dotVal / (v1.mag() * v2.mag());
        if (angle <= -1) {
            return Math.PI;
        }
        else if (angle >= 1) {
            return 0;
        }
        return Math.acos(angle);
    }
}
