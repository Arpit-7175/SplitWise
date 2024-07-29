package com.scaler.splitwise.strategies;

import com.scaler.splitwise.models.ExpenseUser;

import java.util.Comparator;

public class AmountComparator implements Comparator<ExpenseUser> {
    @Override
    public int compare(ExpenseUser o1, ExpenseUser o2) {
        return o1.getAmount()-o2.getAmount();
    }
}
