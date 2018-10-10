package com.baro;

import java.util.List;

public class FamilyRental implements Rental {
    private final double DISCOUNT_RATE = 0.3;
    private List<SingleRental> rentals;

    public FamilyRental(List<SingleRental> rentals) throws IllegalRentalException{
        if (rentals.size() < 3 || rentals.size() > 5) {
            throw new IllegalRentalException("Only 3 to 5 Rentals accepted to create a FamilyRental");
        }
        this.rentals = rentals;
    }


    @Override
    public double getPrice() {
        double price = 0;
        for (Rental r: rentals) {
            price += r.getPrice();
        }
        return price * (1 - DISCOUNT_RATE);
    }
}
