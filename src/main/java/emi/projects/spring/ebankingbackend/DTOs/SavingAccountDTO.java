package emi.projects.spring.ebankingbackend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import emi.projects.spring.ebankingbackend.entities.AccountOperation;
import emi.projects.spring.ebankingbackend.entities.Customer;
import emi.projects.spring.ebankingbackend.enums.AccountStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * author HP
 **/

public class SavingAccountDTO extends BankAccountDTO {

    private String id;
    private double balance;

    private AccountStatus status;
    private Date createdAt;
    private double InterestRate;
    private CustomerDTO customerDTO;

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

    public double getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(double interestRate) {
        InterestRate = interestRate;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
