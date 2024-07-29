package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private int amount;
    private User from;
    private User to;
}
