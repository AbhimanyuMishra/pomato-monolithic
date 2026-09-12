package pomato.app.customer.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pomato.app.customer.Entity.Customer;
import pomato.app.customer.dto.Request.CustomerRequestDto;
import pomato.app.customer.dto.Response.CustomerResponseDto;
import pomato.app.customer.services.CustomerService;

@RestController
public class CustomerController {
 private final CustomerService customerService;

 public CustomerController(CustomerService customerService){
     this.customerService=customerService;
}

@PostMapping("/api/users")
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto requestDto){
     Customer customer = new Customer();

     customer.setName(requestDto.getName());
     customer.setEmail(requestDto.getEmail());
     customer.setPassword(requestDto.getPassword());
     customer.setAge(requestDto.getAge());

     Customer savedCustomer =customerService.createUser(customer);
    CustomerResponseDto responseDto = new CustomerResponseDto();
    responseDto.setId(savedCustomer.getUserId());
    responseDto.setName(savedCustomer.getName());
    responseDto.setEmail(savedCustomer.getEmail());

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

}

@GetMapping("/api/users/{userid}")
public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable("userid") Long userId){

   Customer customer = customerService.findById(userId);
   CustomerResponseDto responseDto = new CustomerResponseDto();

    responseDto.setName(customer.getName());
    responseDto.setEmail(customer.getEmail());

    return ResponseEntity.status(HttpStatus.OK).body(responseDto);


}





}
