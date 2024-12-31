package emi.projects.spring.ebankingbackend;

import emi.projects.spring.ebankingbackend.DTOs.CurrentAccountDTO;
import emi.projects.spring.ebankingbackend.DTOs.CustomerDTO;
import emi.projects.spring.ebankingbackend.DTOs.SavingAccountDTO;
import emi.projects.spring.ebankingbackend.entities.AccountOperation;
import emi.projects.spring.ebankingbackend.entities.CurrentAccount;
import emi.projects.spring.ebankingbackend.entities.Customer;
import emi.projects.spring.ebankingbackend.entities.SavingAccount;
import emi.projects.spring.ebankingbackend.enums.AccountStatus;
import emi.projects.spring.ebankingbackend.enums.OperationType;
import emi.projects.spring.ebankingbackend.repositories.AccountOperationRepository;
import emi.projects.spring.ebankingbackend.repositories.BankAccountRepository;
import emi.projects.spring.ebankingbackend.repositories.CustomerRepository;
import emi.projects.spring.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("youssef","ahmed","adam").forEach(name->{
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer -> {
                bankAccountService.saveCurentAccount(Math.random()*5000,9000, customer.getId());
                bankAccountService.saveSavingAccount(Math.random()*5000,5.5, customer.getId());
                bankAccountService.listBankAccounts().forEach(account->{
                    for (int i=0; i<10 ; i++){
                        String accountId;
                        if (account instanceof CurrentAccountDTO){

                            accountId=((CurrentAccountDTO) account).getId();
                        }else{

                            accountId=((SavingAccountDTO) account).getId();
                        }
                        bankAccountService.credit(accountId,1000+Math.random()*200,"credit");
                        bankAccountService.debit(accountId,1000+Math.random()*200,"credit");

                    }
                });
            });
        };
    }





   // @Bean
    CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
                             AccountOperationRepository accountOperationRepository) {
        return args -> {

           Stream.of("youssef","ahmed","adam").forEach(name->{
               Customer customer = new Customer();
               customer.setName(name);
               customer.setEmail(name+"@gmail.com");
               customerRepository.save(customer);
           });
           customerRepository.findAll().forEach(cust->{
               CurrentAccount currentAccount = new CurrentAccount();
               currentAccount.setCustomer(cust);
               currentAccount.setId(UUID.randomUUID().toString());
               currentAccount.setBalance(Math.random()*9000);
               currentAccount.setStatus(AccountStatus.CREATED);
               currentAccount.setCreatedAt(new Date());
               currentAccount.setOverdraft(9000);
               bankAccountRepository.save(currentAccount);

               SavingAccount savingAccount = new SavingAccount();
               savingAccount.setCustomer(cust);
               savingAccount.setId(UUID.randomUUID().toString());
               savingAccount.setBalance(Math.random()*9000);
               savingAccount.setStatus(AccountStatus.CREATED);
               savingAccount.setCreatedAt(new Date());
               savingAccount.setInterestRate(5.5);
               bankAccountRepository.save(savingAccount);
           });

           bankAccountRepository.findAll().forEach(bankAccount->{
                for (int i=0;i<5;i++){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setBankAccount(bankAccount);
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setAmount(Math.random()*9000);
                    accountOperationRepository.save(accountOperation);
                }
           });

        };
    }
}
