package emi.projects.spring.ebankingbackend.mappers;

import emi.projects.spring.ebankingbackend.DTOs.*;
import emi.projects.spring.ebankingbackend.entities.*;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * author HP
 **/
@Service
public class BankAccountMapperImp {

    public CustomerDTO ToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }
    public Customer ToCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }
    public SavingAccountDTO ToSavingAccountDTO(SavingAccount savingAccount) {
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        savingAccountDTO.setCustomerDTO(ToCustomerDTO(savingAccount.getCustomer()));
        return savingAccountDTO;

    }
    public CurrentAccountDTO ToCurrentAccountDTO(CurrentAccount currentAccount) {
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        currentAccountDTO.setCustomerDTO(ToCustomerDTO(currentAccount.getCustomer()));
        return currentAccountDTO;

    }
    public CurrentAccount ToCurrentAccountEntity(CurrentAccountDTO currentAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        currentAccount.setCustomer(ToCustomerEntity(currentAccountDTO.getCustomerDTO()));
        return currentAccount;
    }
    public SavingAccount ToSavingAccountEntity(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        savingAccount.setCustomer(ToCustomerEntity(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public AccountOperationDTO ToBankAccounOperationtDTO(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;

    }
}
