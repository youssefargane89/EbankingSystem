package emi.projects.spring.ebankingbackend.web;

import emi.projects.spring.ebankingbackend.DTOs.AccountOperationDTO;
import emi.projects.spring.ebankingbackend.DTOs.BankAccountDTO;
import emi.projects.spring.ebankingbackend.entities.SavingAccount;
import emi.projects.spring.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author HP
 **/
@RestController
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/bankaccounts/{id}")
   public BankAccountDTO getBankAccountDTO(@PathVariable String id) {
        return bankAccountService.getBankAccount(id);
    }
    @GetMapping("/bankaccounts")
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountService.listBankAccounts();
    }
    @GetMapping("/bankaccounts/{id}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String id) {
        return bankAccountService.AccountHistory(id);
    }

}
