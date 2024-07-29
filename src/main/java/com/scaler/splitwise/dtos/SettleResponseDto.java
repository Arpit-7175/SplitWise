package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleResponseDto {
    private List<Transaction> transactionList;
}
