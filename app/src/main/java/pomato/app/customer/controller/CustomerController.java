package pomato.app.customer.controller;

import org.springframework.web.bind.annotation.RestController;
import pomato.app.customer.services.CustomerService;

@RestController
public class CustomerController {
 private final CustomerService customerService;

 public CustomerController(CustomerService customerService){
     this.customerService=customerService;
}

//@PostMapping("/api/users")
//    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto requestDto){
//     Users users = new Users();
//
//     users.setName(requestDto.getName());
//     users.setEmail(requestDto.getEmail());
//     users.setPassword(requestDto.getPassword());
//     users.setAge(requestDto.getAge());
//
//     Users savedUsers =customerService.createUser(users);
//    CustomerResponseDto responseDto = new CustomerResponseDto();
//    responseDto.setId(savedUsers.getUserId());
//    responseDto.setName(savedUsers.getName());
//    responseDto.setEmail(savedUsers.getEmail());
//
//    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
//
//}
//
//@GetMapping("/api/users/{userid}")
//public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable("userid") Long userId){
//
//   Users users = customerService.findById(userId);
//   CustomerResponseDto responseDto = new CustomerResponseDto();
//
//    responseDto.setName(users.getName());
//    responseDto.setEmail(users.getEmail());
//
//    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//
//
//}





}
