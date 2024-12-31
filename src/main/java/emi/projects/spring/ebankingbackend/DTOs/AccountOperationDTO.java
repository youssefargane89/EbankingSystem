package emi.projects.spring.ebankingbackend.DTOs;

import emi.projects.spring.ebankingbackend.entities.BankAccount;
import emi.projects.spring.ebankingbackend.enums.OperationType;
import jakarta.persistence.*;

import java.util.Date;

/**
 * author HP
 **/

public class AccountOperationDTO {

    private Long id;

    private OperationType type;
    private Date operationDate;
    private double amount;
    private String description;
    public AccountOperationDTO() {}

    public AccountOperationDTO(Long id, OperationType type, Date operationDate, double amount, String description) {
        this.id = id;
        this.type = type;
        this.operationDate = operationDate;
        this.amount = amount;
       this.description=description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
