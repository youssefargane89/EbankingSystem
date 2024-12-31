package emi.projects.spring.ebankingbackend.services;

import emi.projects.spring.ebankingbackend.DTOs.*;

import java.util.List;

/**
 * author HP
 **/
public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    CurrentAccountDTO saveCurentAccount(double initialBalance, double overDraft, Long customerId);
    SavingAccountDTO saveSavingAccount(double initialBalance, double InterestRate, Long customerId);
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId);
    void debit(String accountId, double amount,String description);
    void credit(String accountId, double amount,String description);
    void transfer(String accountIdsource,String accountIdDestination,double amount);
    List<BankAccountDTO> listBankAccounts();


    CustomerDTO getcustomer(Long customerId);

    List<AccountOperationDTO> AccountHistory(String accountId);
}
