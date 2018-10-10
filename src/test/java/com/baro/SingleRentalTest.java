package com.baro;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class SingleRentalTest {

    @Test(expected = IllegalRentalException.class)
    public void createIllegalSingleRentalNegativeDuration() throws IllegalRentalException{
        Instant startTime = Instant.now();
        new SingleRental(startTime, startTime.minus(1, ChronoUnit.DAYS));
    }

    @Test(expected = IllegalRentalException.class)
    public void createIllegalSingleRentalZeroDuration() throws IllegalRentalException{
        Instant startTime = Instant.now();
        new SingleRental(startTime, startTime);
    }

    @Test
    public void accurateSingleRentalPricingHourly() throws IllegalRentalException {
        Instant startTime = Instant.now();
        SingleRental rental;

        rental = new SingleRental(startTime, startTime.plus(10, ChronoUnit.MINUTES));
        assertEquals(5d, rental.getPrice(), 0d);

        rental = new SingleRental(startTime, startTime.plus(1, ChronoUnit.HOURS));
        assertEquals(5d, rental.getPrice(), 0d);

        rental = new SingleRental(startTime, startTime.plus(1, ChronoUnit.HOURS)
                                                    .plus(59, ChronoUnit.MINUTES));
        assertEquals(2d * 5d, rental.getPrice(), 0d);

        rental = new SingleRental(startTime, startTime.plus(2, ChronoUnit.HOURS));
        assertEquals(2d * 5d, rental.getPrice(), 0d);

        rental = new SingleRental(startTime, startTime.plus(23, ChronoUnit.HOURS)
                                                    .plus(59, ChronoUnit.MINUTES));
        assertEquals(24d * 5d, rental.getPrice(), 0d);
    }

    @Test
    public void accurateSingleRentalPricingDaily() throws IllegalRentalException {
        Instant startTime = Instant.now();
        SingleRental rental;

        rental = new SingleRental(startTime, startTime.plus(1, ChronoUnit.DAYS));
        assertEquals(20d, rental.getPrice(), 0d);

        rental = new SingleRental(startTime, startTime.plus(6, ChronoUnit.DAYS)
                                                    .plus(10, ChronoUnit.HOURS));
        assertEquals(7d * 20d, rental.getPrice(), 0d);
    }

    @Test
    public void accurateSingleRentalPricingWeekly() throws IllegalRentalException {
        Instant startTime = Instant.now();
        SingleRental rental;

        rental = new SingleRental(startTime, startTime.plus(7, ChronoUnit.DAYS));
        assertEquals(60d, rental.getPrice(), 0d);

        rental = new SingleRental(startTime, startTime.plus(8, ChronoUnit.DAYS));
        assertEquals(2d * 60d, rental.getPrice(), 0d);
    }
}
