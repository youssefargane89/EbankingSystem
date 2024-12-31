package emi.projects.spring.ebankingbackend.entities;

import emi.projects.spring.ebankingbackend.enums.OperationType;
import jakarta.persistence.*;

import java.util.Date;

/**
 * author HP
 **/
@Entity
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private Date operationDate;
    private double amount;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;
    public AccountOperation() {}

    public AccountOperation(Long id, OperationType type, Date operationDate, double amount, BankAccount bankAccount) {
        this.id = id;
        this.type = type;
        this.operationDate = operationDate;
        this.amount = amount;
        this.bankAccount = bankAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
