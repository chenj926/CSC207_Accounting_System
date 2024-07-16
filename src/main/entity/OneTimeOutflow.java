package entity;

import java.time.LocalDate;

public class OneTimeOutflow extends OneTimeTransaction {
    public OneTimeOutflow(String identification, float amount, LocalDate date, String description, String category) {
        super(identification, amount, date, description, category);
    }
}
