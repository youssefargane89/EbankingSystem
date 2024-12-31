package emi.projects.spring.ebankingbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.util.List;

/**
 * author HP
 **/
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String email;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<BankAccount> Accounts;

    public Customer() {}
    public Customer(Long id, String name, String email, List<BankAccount> accounts) {
        this.id = id;
        Name = name;
        this.email = email;
        Accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BankAccount> getAccounts() {
        return Accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        Accounts = accounts;
    }
}
