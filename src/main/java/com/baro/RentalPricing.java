package com.baro;

import java.math.BigDecimal;
import java.time.Duration;

public enum RentalPricing {
    HOURLY(BigDecimal.valueOf(5)) {
        @Override
        public BigDecimal getPrice(Duration duration) {
            return getRate().multiply(BigDecimal.valueOf(Math.ceil(duration.toMinutes() / 60f)));
        }
    },
    DAILY(BigDecimal.valueOf(20)) {
        @Override
        public BigDecimal getPrice(Duration duration) {
            return getRate().multiply(BigDecimal.valueOf(Math.ceil(duration.toHours() / 24f)));
        }
    },
    WEEKLY(BigDecimal.valueOf(60)) {
        @Override
        public BigDecimal getPrice(Duration duration) {
            return getRate().multiply(BigDecimal.valueOf(Math.ceil(duration.toDays() / 7f)));
        }
    };

    private BigDecimal rate;

    RentalPricing(BigDecimal rate) {
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

    public abstract BigDecimal getPrice(Duration duration);

    protected BigDecimal getRate() { return rate; }
}
