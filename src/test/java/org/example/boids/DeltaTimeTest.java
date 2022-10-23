package org.example.boids;

import java.time.Clock;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeltaTimeTest {
    @Test
    public void testGetDeltaTime() {
        DeltaTime dt = new DeltaTime();
        assert dt.getDeltaTime() == 0;
    }

    @Test
    public void testUpdate() {
        Clock mockedClock = mock(Clock.class);
        Instant testInstant = Instant.now();
        when(mockedClock.instant())
            .thenReturn(testInstant);
        DeltaTime dt = new DeltaTime(mockedClock);
        assert dt.getDeltaTime() == 0;
        assert dt.update() == 0;
        when(mockedClock.instant())
            .thenReturn(testInstant.plusSeconds(1));
        assert dt.update() == 1;
        when(mockedClock.instant())
            .thenReturn(testInstant.plusMillis(2732));
        assert dt.update() == 1.732;
    }
}
