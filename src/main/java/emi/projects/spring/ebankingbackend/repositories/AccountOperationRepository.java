package emi.projects.spring.ebankingbackend.repositories;

import emi.projects.spring.ebankingbackend.entities.AccountOperation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author HP
 **/
public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
     List<AccountOperation> findByBankAccountId(String accountId);
}
