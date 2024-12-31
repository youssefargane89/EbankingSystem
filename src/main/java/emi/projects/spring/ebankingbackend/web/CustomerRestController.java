package emi.projects.spring.ebankingbackend.web;

import emi.projects.spring.ebankingbackend.DTOs.CustomerDTO;
import emi.projects.spring.ebankingbackend.entities.Customer;
import emi.projects.spring.ebankingbackend.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * author HP
 **/
@RestController

public class CustomerRestController {

     private BankAccountService bankAccountService;

    public CustomerRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers() {
        return bankAccountService.listCustomers();

    }
    @GetMapping ("/customers/{id}")
    public CustomerDTO getcustomer(@PathVariable(name = "id") Long customerId) {
        return bankAccountService.getcustomer(customerId);

    }
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(id);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
           bankAccountService.deleteCustomer(id);
    }
}
