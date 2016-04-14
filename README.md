# bank-kata
Bank kata starting point. From a [tutorial by Sandro Mancuso](https://github.com/sandromancuso/Bank-kata).

[![Build Status](https://travis-ci.org/fmacicasan/bank-kata.svg?branch=master)](https://travis-ci.org/fmacicasan/bank-kata)


Design, Develop and Test enough code to make the following integration test pass
```java   
    public void printedStatementShouldContainAccountOperations() {
         account.deposit(1000);
         account.withdraw(100);
         account.deposit(500); 
         
         account.printStatement();
         
         InOrder inOrder = inOrder(console);
         inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
         inOrder.verify(console).printLine("10/04/2016 | 500.00 | 1400.00");
         inOrder.verify(console).printLine("02/04/2016 | -100.00 | 900.00");
         inOrder.verify(console).printLine("01/04/2016 | 1000.00 | 1000.00");
         inOrder.verifyNoMoreInteractions();
     }
```
Things to consider

- Who is responsible to attach the date to account operations
- Who is responsible for flipping the sign of withdraw operations
- Who is responsible for imposing the order of operations in the report
- Who is responsible for computing the balance
