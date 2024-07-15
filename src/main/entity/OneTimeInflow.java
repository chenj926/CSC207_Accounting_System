package entity;

import java.time.LocalDate;

public class OneTimeInflow extends OneTimeTransaction {
    public OneTimeInflow(String identification, float amount, LocalDate date, String description, String category) {
        super(identification, amount, date, description, category);
    }
}
/*
public class OneTimeInflow extends OneTimeTransaction {
    public OneTimeInflow(float transactionAmount){
        super(transactionAmount)
    }

    @Override
    punlic void recordTransaction(float amount) {
        super.recordTransaction(amount);
        System.out.println("one-time inflow: " +amount);
    }
}

 */