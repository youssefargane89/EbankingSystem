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
@DiscriminatorValue("CA")
public class CurrentAccount extends BankAccount{
    private double overdraft;

    public CurrentAccount() {}
    public CurrentAccount(double overdraft) {
        this.overdraft = overdraft;
    }

    public CurrentAccount(String id, double balance, AccountStatus status, Date createdAt, Customer customer, List<AccountOperation> accountOperations, double overdraft) {
        super(id, balance, status, createdAt, customer, accountOperations);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}
