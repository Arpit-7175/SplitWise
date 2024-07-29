package com.scaler.splitwise.commands;

public interface Command {
    public void execute(String input);
    public boolean matches(String input);
}
