package com.scaler.splitwise.strategies;

import com.scaler.splitwise.dtos.Transaction;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.ExpenseUser;
import com.scaler.splitwise.models.ExpenseUserType;
import com.scaler.splitwise.models.User;

import java.util.*;

public interface SettleUpStrategy {
    public default List<Transaction> settle(List<Expense> expenses){

        HashMap<User,Integer> map=new HashMap<>();
        for(Expense exp:expenses) {
            User u = exp.getCreatedBy();
            if (exp.getExpenseUser().get(0).getExpenseUserType().equals(ExpenseUserType.PAID)) {
                if (map.get(u) == null) {
                    map.put(u, exp.getAmount());
                } else {
                    int val = map.get(u);
                    map.put(u, val + exp.getAmount());
                }
            } else if (exp.getExpenseUser().get(0).getExpenseUserType().equals(ExpenseUserType.HAD_TO_PAY)) {
                if (map.get(u) == null) {
                    map.put(u, -exp.getAmount());
                } else {
                    int val = map.get(u);
                    map.put(u, exp.getAmount() - val);
                }
            }
        }
        PriorityQueue<ExpenseUser> pq_paidBy = new
                    PriorityQueue<>(5, new AmountComparator());
        PriorityQueue<ExpenseUser> pq_HadToPay = new
                    PriorityQueue<>(5, new AmountComparator());

        for (User u : map.keySet()) {
            int val = map.get(u);
            System.out.println(val);
            if (val < 0) {
                    ExpenseUser e = new ExpenseUser();
                    e.setUser(u);
                    e.setAmount(-val);
                    pq_HadToPay.add(e);
            } else {
                    ExpenseUser e = new ExpenseUser();
                    e.setUser(u);
                    e.setAmount(val);
                    pq_paidBy.add(e);
                }
            }

        Iterator value1 = pq_paidBy.iterator();
        Iterator value2 = pq_HadToPay.iterator();

        ArrayList<Transaction> transactions=new ArrayList<>();
        while (value1.hasNext() && value2.hasNext()) {
            ExpenseUser a = pq_paidBy.poll();
            ExpenseUser b = pq_HadToPay.poll();
            Transaction t1=new Transaction();
            if (a.getAmount() < b.getAmount()) {
                    t1.setAmount(a.getAmount());
                    t1.setTo(a.getUser());
                    t1.setFrom(b.getUser());
                    b.setAmount(b.getAmount() - a.getAmount());
                    pq_HadToPay.add(b);
            } else {
                t1.setAmount(b.getAmount());
                t1.setTo(a.getUser());
                t1.setFrom(b.getUser());
                a.setAmount(a.getAmount() - b.getAmount());
                pq_paidBy.add(a);
                }
            transactions.add(t1);
            }
        return transactions;
    }
}
