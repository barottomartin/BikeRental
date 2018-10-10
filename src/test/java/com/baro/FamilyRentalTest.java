package com.baro;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FamilyRentalTest {

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
    public void accurateFamilyRentalPricing() throws IllegalRentalException {
        Instant startTime = Instant.now();
        List<SingleRental> rentals = new ArrayList<>();
        rentals.add(new SingleRental(startTime, startTime.plus(4, ChronoUnit.HOURS)));
        rentals.add(new SingleRental(startTime, startTime.plus(3, ChronoUnit.DAYS)));
        rentals.add(new SingleRental(startTime, startTime.plus(14, ChronoUnit.DAYS)));
        FamilyRental familyRental = new FamilyRental(rentals);
        // Estimated price = ( (4*5 + 3*20 + 2*60) * 0.7)
        assertEquals(0, BigDecimal.valueOf(140).compareTo(familyRental.getPrice()));
    }

}
