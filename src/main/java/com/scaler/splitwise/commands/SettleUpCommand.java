package com.scaler.splitwise.commands;


import com.scaler.splitwise.controllers.SettleUpController;
import com.scaler.splitwise.dtos.SettleRequestDto;
import com.scaler.splitwise.dtos.SettleResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpCommand implements Command {
    private SettleUpController settleUpController;

    public SettleUpCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public void execute(String input) {
        List<String> tokens = List.of(input.split(" "));
        Long userId = Long.valueOf(tokens.get(0));

        SettleRequestDto requestDto = new SettleRequestDto();
        requestDto.setUserId(userId);

        SettleResponseDto responseDto = settleUpController
                .settleUser(requestDto);
    }

    @Override
    public boolean matches(String input) {
        List<String> tokens = List.of(input.split(" "));

        return tokens.size() == 2 && tokens.get(1)
                .equalsIgnoreCase(CommandKeywords.settleUp);
    }
}
