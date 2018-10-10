package com.baro;

import java.time.Duration;

public enum RentalPricing {
    HOURLY(5d) {
        @Override
        public double getPrice(Duration duration) {
            return Math.ceil(duration.toMinutes() / 60d) * getRate();
        }
    },
    DAILY(20d) {
        @Override
        public double getPrice(Duration duration) {
            return Math.ceil(duration.toHours() / 24d) * getRate();
        }
    },
    WEEKLY(60d) {
        @Override
        public double getPrice(Duration duration) {
            return Math.ceil(duration.toDays() / 7d) * getRate();
        }
    };

    private double rate;

    RentalPricing(double rate) {
        this.rate = rate;
    }

    public static RentalPricing getRentalPricingFor(Duration duration) {
        if (duration.toDays() >= 7) {
            return WEEKLY;
        } else if (duration.toDays() >= 1) {
            return DAILY;
        }
        return HOURLY;
    }

    public abstract double getPrice(Duration duration);

    protected double getRate() { return rate; }
}
