package com.craft.domain;

public interface Account {

    void deposit(int amount);

    void withdraw(int amount);

    void printStatement();
}