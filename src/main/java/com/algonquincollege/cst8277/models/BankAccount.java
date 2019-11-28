package com.algonquincollege.cst8277.models;

/***
 * BankAccount interface.
 * @author Jack Tan
 * @date 2019/11/15
 */
public interface BankAccount extends BankEntity {

    public double getBalance();
    public void setBalance(double balance);
    
}