package com.baro;

import java.time.Duration;

public enum RentalPricing {
    HOURLY(5d) {
        @Override
        public boolean isWithinRange(Duration duration) {
            return duration.toDays() < 1;
        }

        @Override
        public double getPrice(Duration duration) {
            return Math.ceil(duration.toMinutes() / 60d) * getRate();
        }
    },
    DAILY(20d) {
        @Override
        public boolean isWithinRange(Duration duration) {
            return duration.toDays() >= 1 && duration.toDays() < 7;
        }

        @Override
        public double getPrice(Duration duration) {
            return Math.ceil(duration.toHours() / 24d) * getRate();
        }
    },
    WEEKLY(60d) {
        @Override
        public boolean isWithinRange(Duration duration) {
            return duration.toDays() >= 7;
        }

        @Override
        public double getPrice(Duration duration) {
            return Math.ceil(duration.toDays() / 7d) * getRate();
        }
    };

    private double rate;

    RentalPricing(double rate) {
        this.rate = rate;
    }

    public abstract boolean isWithinRange(Duration duration);

    public abstract double getPrice(Duration duration);

    protected double getRate() { return rate; }
}
