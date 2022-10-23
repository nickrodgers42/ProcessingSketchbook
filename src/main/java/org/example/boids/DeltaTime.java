package org.example.boids;

import java.time.Duration;
import java.time.Instant;

public class DeltaTime {
    private Duration deltaTime = Duration.ZERO;
    private Instant lastFrame;

    public DeltaTime() {}

    public Duration getDeltaTime() {
        return deltaTime;
    }

    public void update() {
        Instant thisFrame = Instant.now();
        if (lastFrame == null) {
            lastFrame = thisFrame;
        }
        deltaTime = Duration.between(lastFrame, thisFrame);
        lastFrame = thisFrame;
    }
}