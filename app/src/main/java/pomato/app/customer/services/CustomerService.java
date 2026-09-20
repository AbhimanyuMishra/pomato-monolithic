package pomato.app.customer.services;

import org.springframework.stereotype.Service;
import pomato.app.customer.Entity.Users;
import pomato.app.customer.repository.UserRepository;


@Service
public class CustomerService {
    private final UserRepository userRepository;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users createUser(Users users){
       return   userRepository.save(users);
    }

    public Users findById(Long id){
        return userRepository.findById(id).get();
    }
}
