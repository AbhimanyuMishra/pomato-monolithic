package pomato.app.customer.services;

import org.springframework.stereotype.Service;
import pomato.app.customer.Entity.Customer;
import pomato.app.customer.repository.CustomerRepository;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository  customerRepository) {
        this.customerRepository =  customerRepository;
    }

    public Customer createUser(Customer customer){
       return   customerRepository.save(customer);
    }

    public Customer findById(Long id){
        return customerRepository.findById(id).get();
    }
}
