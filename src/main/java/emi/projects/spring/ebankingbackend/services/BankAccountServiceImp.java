package emi.projects.spring.ebankingbackend.services;

import emi.projects.spring.ebankingbackend.DTOs.*;
import emi.projects.spring.ebankingbackend.entities.*;
import emi.projects.spring.ebankingbackend.enums.OperationType;
import emi.projects.spring.ebankingbackend.mappers.BankAccountMapperImp;
import emi.projects.spring.ebankingbackend.repositories.AccountOperationRepository;
import emi.projects.spring.ebankingbackend.repositories.BankAccountRepository;
import emi.projects.spring.ebankingbackend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * author HP
 **/
@Service
@Transactional
public class BankAccountServiceImp implements BankAccountService {

    private CustomerRepository customerRepository;

    private BankAccountRepository bankAccountRepository;

    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImp mapper;


    Logger log=Logger.getLogger(this.getClass().getName());

    public BankAccountServiceImp(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, AccountOperationRepository accountOperationRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.mapper = new BankAccountMapperImp();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("saving customer");
        Customer customer=mapper.ToCustomerEntity(customerDTO);
        Customer savedcustomer= customerRepository.save(customer);
        return mapper.ToCustomerDTO(savedcustomer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("saving customer");
        Customer customer=mapper.ToCustomerEntity(customerDTO);
        Customer savedcustomer= customerRepository.save(customer);
        return mapper.ToCustomerDTO(savedcustomer);
    }
    @Override
    public void deleteCustomer(Long id) {
        log.info("deleting customer");
        customerRepository.deleteById(id);
    }

    @Override
    public CurrentAccountDTO saveCurentAccount(double initialBalance, double overDraft, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }


        CurrentAccount bankaccount= new CurrentAccount();;
        bankaccount.setBalance(initialBalance);
        bankaccount.setCustomer(customer);
        bankaccount.setOverdraft(overDraft);
        bankaccount.setId(UUID.randomUUID().toString());
        bankaccount.setCreatedAt(new Date());
        bankAccountRepository.save(bankaccount);
        CurrentAccount savedaccount = bankAccountRepository.save(bankaccount);
        return mapper.ToCurrentAccountDTO(savedaccount);
    }

    @Override
    public SavingAccountDTO saveSavingAccount(double initialBalance, double InterestRate, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }


        SavingAccount bankaccount= new SavingAccount();;
        bankaccount.setBalance(initialBalance);
        bankaccount.setCustomer(customer);
        bankaccount.setInterestRate(InterestRate);
        bankaccount.setId(UUID.randomUUID().toString());
        bankaccount.setCreatedAt(new Date());
        SavingAccount savedaccount = bankAccountRepository.save(bankaccount);


        return mapper.ToSavingAccountDTO(savedaccount);
    }


    @Override
    public List<CustomerDTO> listCustomers() {
       List<Customer>  Customers= customerRepository.findAll();
       List<CustomerDTO> customerDTOs= Customers.stream().map(customer->mapper.ToCustomerDTO(customer)).collect(Collectors.toList());
       return customerDTOs;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
           if (bankAccount instanceof SavingAccount){
               return mapper.ToSavingAccountDTO((SavingAccount) bankAccount);
           }else{
               return mapper.ToCurrentAccountDTO((CurrentAccount) bankAccount);
           }

    }

    @Override
    public void debit(String accountId, double amount, String description) {
        BankAccount bankaccount=bankAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if (bankaccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setBankAccount(bankaccount);
        accountOperationRepository.save(accountOperation);
        bankaccount.setBalance(bankaccount.getBalance() - amount);
        bankAccountRepository.save(bankaccount);


    }

    @Override
    public void credit(String accountId, double amount, String description) {
        BankAccount bankaccount=bankAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setBankAccount(bankaccount);
        accountOperationRepository.save(accountOperation);
        bankaccount.setBalance(bankaccount.getBalance() + amount);
        bankAccountRepository.save(bankaccount);
    }

    @Override
    public void transfer(String accountIdsource, String accountIdDestination, double amount) {
         debit(accountIdsource, amount, "transfer to"+accountIdDestination);
        credit(accountIdsource, amount, "transfer form"+accountIdsource);


    }

    @Override
    public List<BankAccountDTO> listBankAccounts() {
       List<BankAccount> bankAccounts= bankAccountRepository.findAll();
       List<BankAccountDTO> bankAccountDTOS=bankAccounts.stream().map(acc->{
                           if (acc instanceof SavingAccount){
                                return mapper.ToSavingAccountDTO((SavingAccount) acc);
                           }else{
                               return mapper.ToCurrentAccountDTO((CurrentAccount) acc);
                           }
       }).collect(Collectors.toList());

        return bankAccountDTOS;

    }

    @Override
    public CustomerDTO getcustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapper.ToCustomerDTO(customer);
    }
    @Override
    public List<AccountOperationDTO> AccountHistory(String accountId) {
        List<AccountOperation> operations= accountOperationRepository.findByBankAccountId(accountId);
        List<AccountOperationDTO> operationDTOs=operations.stream().map(op->{
            return mapper.ToBankAccounOperationtDTO(op);

        }).collect(Collectors.toList());
        return operationDTOs;
    }
}
