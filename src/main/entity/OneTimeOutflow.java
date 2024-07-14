package entity;

import java.time.LocalDate;

public class OneTimeOutflow extends OneTimeTransaction {
    public OneTimeOutflow(String identification, double amount, LocalDate date, String description) {
        super(identification, amount, date, description);
        if(this.isInflow()) System.out.println("Periodic outflow error, should be inflow!");
    }
}
/*
public class OneTimeOutflow extends OneTimeTransaction {
    public OneTimeOutflow(float transactionAmount){
        super(transactionAmount);
    }
    @Override
    punlic void recordTransaction(float amount) {
        super.recordTransaction(amount);
        System.out.println("one-time outflow: " + amount);
    }
}

 */
