package org.example.boids;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class DeltaTime {
    private Duration frameDuration = Duration.ZERO;
    private Instant lastFrame;
    private Clock clock;

    public DeltaTime() {
        this.clock = Clock.systemUTC();
    }

    public DeltaTime(Clock clock) {
        this.clock = clock;
    }

    /**
    * Returns the time in seconds between the last two calls to the update
    * method
    */
    public double getDeltaTime() {
        return frameDuration.toNanos() / 1000000000.0;
    }

    public double update() {
        Instant thisFrame = this.clock.instant();
        if (lastFrame == null) {
            lastFrame = thisFrame;
        }
        this.frameDuration = Duration.between(lastFrame, thisFrame);
        lastFrame = thisFrame;
        return this.getDeltaTime();
    }
}
