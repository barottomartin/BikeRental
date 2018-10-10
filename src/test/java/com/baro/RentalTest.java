package com.baro;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RentalTest {

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

    @Test(expected = IllegalRentalException.class)
    public void createIllegalFamilyRentalByInsufficiency() throws IllegalRentalException {
        Instant startTime = Instant.now();
        List<SingleRental> rentals = new ArrayList<>();
        rentals.add(new SingleRental(startTime, startTime.plus(1, ChronoUnit.MINUTES)));
        new FamilyRental(rentals);
    }

    @Test(expected = IllegalRentalException.class)
    public void createIllegalFamilyRentalByExcess() throws IllegalRentalException {
        Instant startTime = Instant.now();
        List<SingleRental> rentals = new ArrayList<>();
        rentals.add(new SingleRental(startTime, startTime.plus(1, ChronoUnit.MINUTES)));
        rentals.add(new SingleRental(startTime, startTime.plus(1, ChronoUnit.MINUTES)));
        rentals.add(new SingleRental(startTime, startTime.plus(1, ChronoUnit.MINUTES)));
        rentals.add(new SingleRental(startTime, startTime.plus(1, ChronoUnit.MINUTES)));
        rentals.add(new SingleRental(startTime, startTime.plus(1, ChronoUnit.MINUTES)));
        rentals.add(new SingleRental(startTime, startTime.plus(1, ChronoUnit.MINUTES)));
        new FamilyRental(rentals);
    }

    @Test
    public void accurateSingleRentalPricing() throws IllegalRentalException {
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

        rental = new SingleRental(startTime, startTime.plus(1, ChronoUnit.DAYS));
        assertEquals(20d, rental.getPrice(), 0d);

        rental = new SingleRental(startTime, startTime.plus(7, ChronoUnit.DAYS));
        assertEquals(60d, rental.getPrice(), 0d);
    }

    @Test
    public void accurateFamilyRentalPricing() throws IllegalRentalException {
        //TODO
        assertTrue(false);
    }
}
