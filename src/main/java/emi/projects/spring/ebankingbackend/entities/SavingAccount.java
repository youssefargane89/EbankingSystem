package emi.projects.spring.ebankingbackend.entities;

import emi.projects.spring.ebankingbackend.enums.AccountStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

/**
 * author HP
 **/
@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends BankAccount{
    private double interestRate;

    public SavingAccount() {}

    public SavingAccount(double interestRate) {
        this.interestRate = interestRate;
    }

    public SavingAccount(String id, double balance, AccountStatus status, Date createdAt, Customer customer, List<AccountOperation> accountOperations, double interestRate) {
        super(id, balance, status, createdAt, customer, accountOperations);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
