package emi.projects.spring.ebankingbackend.DTOs;

/**
 * author HP
 **/

public class CustomerDTO {

    private Long id;
    private String Name;
    private String email;




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


}
