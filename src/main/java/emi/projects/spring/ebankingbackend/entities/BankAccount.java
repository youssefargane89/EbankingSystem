package emi.projects.spring.ebankingbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import emi.projects.spring.ebankingbackend.enums.AccountStatus;
import jakarta.persistence.*;
import jdk.dynalink.Operation;

import java.util.Date;
import java.util.List;

/**
 * author HP
 **/
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4, discriminatorType = DiscriminatorType.STRING)
public class BankAccount {
    @Id

    private String id;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private Date createdAt;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;
    public BankAccount() {
    }

    public BankAccount(String id, double balance, AccountStatus status, Date createdAt, Customer customer, List<AccountOperation> accountOperations) {
        this.id = id;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
        this.customer = customer;
        this.accountOperations = accountOperations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<AccountOperation> getAccountOperations() {
        return accountOperations;
    }

    public void setAccountOperations(List<AccountOperation> accountOperations) {
        this.accountOperations = accountOperations;
    }
}
