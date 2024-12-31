package emi.projects.spring.ebankingbackend.repositories;

import emi.projects.spring.ebankingbackend.entities.BankAccount;
import emi.projects.spring.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author HP
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
