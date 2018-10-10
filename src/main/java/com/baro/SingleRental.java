package com.baro;

import java.time.Duration;
import java.time.Instant;

public class SingleRental implements Rental{

    private Instant startTime;
    private Instant endTime;

    public SingleRental(Instant startTime, Instant endTime) throws IllegalRentalException{
        if (endTime.isBefore(startTime) || endTime.equals(startTime)){
            throw new IllegalRentalException("endTime should be after startTime");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public double getPrice(){
        Duration duration = Duration.between(startTime, endTime);
        return RentalPricing.getRentalPricingFor(duration).getPrice(duration);
    }

}
