package entity;

import java.time.LocalDate;

public class PeriodicOutflow extends PeriodicTransaction {
    public PeriodicOutflow(String identification, float amount, LocalDate startDate, String description,
                           LocalDate endDate, int duration) {
        super(identification, amount, startDate, description, endDate, duration);
    }
}