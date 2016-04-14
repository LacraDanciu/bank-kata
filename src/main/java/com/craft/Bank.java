package com.craft;

/**
 * @author flo
 * @since 14/04/16.
 */
public class Bank {

    protected static final String BANK_NAME_HEADER = "Bank Name";

    private String name;
    private Console console;

    public Bank (String name, Console console) {
        this.name = name;
        this.console = console;
    }

    public void reportName() {
        this.console.printLine(BANK_NAME_HEADER);
        this.console.printLine(name);
    }

    public static void main(String[] args) {
        Console console = new Console();
        Bank bankOfKata = new Bank("BankOfKata", console);

        bankOfKata.reportName();
    }
}
