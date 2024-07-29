package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.SettleRequestDto;
import com.scaler.splitwise.dtos.SettleResponseDto;
import com.scaler.splitwise.dtos.Transaction;
import com.scaler.splitwise.services.SettleUpService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleResponseDto settleUser(SettleRequestDto requestDto) {
        SettleResponseDto responseDto = new SettleResponseDto();
        List<Transaction> transactionList = settleUpService
                .settleUpUser(requestDto.getUserId());

        responseDto.setTransactionList(transactionList);
        return responseDto;
    }

    public void settleUpGroup() {

    }
}
