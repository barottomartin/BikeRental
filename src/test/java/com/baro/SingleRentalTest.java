package com.baro;

import org.junit.Test;

import java.math.BigDecimal;
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
        assertEquals(0, BigDecimal.valueOf(5).compareTo(rental.getPrice()));

        rental = new SingleRental(startTime, startTime.plus(1, ChronoUnit.HOURS));
        assertEquals(0, BigDecimal.valueOf(5).compareTo(rental.getPrice()));

        rental = new SingleRental(startTime, startTime.plus(1, ChronoUnit.HOURS)
                                                    .plus(59, ChronoUnit.MINUTES));
        assertEquals(0, BigDecimal.valueOf(10).compareTo(rental.getPrice()));

        rental = new SingleRental(startTime, startTime.plus(2, ChronoUnit.HOURS));
        assertEquals(0, BigDecimal.valueOf(10).compareTo(rental.getPrice()));

        rental = new SingleRental(startTime, startTime.plus(23, ChronoUnit.HOURS)
                                                    .plus(59, ChronoUnit.MINUTES));
        assertEquals(0, BigDecimal.valueOf(120).compareTo(rental.getPrice()));
    }

    @Test
    public void accurateSingleRentalPricingDaily() throws IllegalRentalException {
        Instant startTime = Instant.now();
        SingleRental rental;

        rental = new SingleRental(startTime, startTime.plus(1, ChronoUnit.DAYS));
        assertEquals(0, BigDecimal.valueOf(20).compareTo(rental.getPrice()));

        rental = new SingleRental(startTime, startTime.plus(6, ChronoUnit.DAYS)
                                                    .plus(10, ChronoUnit.HOURS));
        assertEquals(0, BigDecimal.valueOf(140).compareTo(rental.getPrice()));
    }

    @Test
    public void accurateSingleRentalPricingWeekly() throws IllegalRentalException {
        Instant startTime = Instant.now();
        SingleRental rental;

        rental = new SingleRental(startTime, startTime.plus(7, ChronoUnit.DAYS));
        assertEquals(0, BigDecimal.valueOf(60).compareTo(rental.getPrice()));

        rental = new SingleRental(startTime, startTime.plus(8, ChronoUnit.DAYS));
        assertEquals(0, BigDecimal.valueOf(120).compareTo(rental.getPrice()));
    }
}
