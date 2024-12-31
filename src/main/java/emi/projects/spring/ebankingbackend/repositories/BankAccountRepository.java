package emi.projects.spring.ebankingbackend.repositories;

import emi.projects.spring.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author HP
 **/
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
