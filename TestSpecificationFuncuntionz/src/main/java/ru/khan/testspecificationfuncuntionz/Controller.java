package ru.khan.testspecificationfuncuntionz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final CustomerRepository customerRepository;

    public Controller(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/create")
    public String create(@RequestBody Customer customer){

        Customer newC = new Customer();
        newC.setFirstName(customer.getFirstName());
        newC.setLastName(customer.getLastName());
        newC.setEmail(customer.getEmail());
        newC.setAge(customer.getAge());
        newC.setProf(customer.getProf());

        try {
            customerRepository.save(newC);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }

        return "create";
    }
    @GetMapping("/get")
    public Page<Customer> get(@RequestBody FilterCustomer f,
                    Pageable pageable){
        return customerRepository.findAll(
                CustomerSpecification.filter(f),
                pageable
        );

    }


}
