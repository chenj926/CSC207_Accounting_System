package entity;

import java.time.LocalDate;

public class PeriodicInflow extends PeriodicTransaction {
    public PeriodicInflow(String identification, float amount, LocalDate startDate, String description,
                          LocalDate endDate, int period) {
        super(identification, amount, startDate, description, endDate, period);
    }
}