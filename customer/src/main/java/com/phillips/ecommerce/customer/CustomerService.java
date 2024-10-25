package com.phillips.ecommerce.customer;

import com.phillips.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var cutomer= repository.findById(request.id())
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("cannot update the customer with id:: %s",request.id())
                ));
        mergeCustomer(cutomer,request);
        repository.save(cutomer);
    }

    private void mergeCustomer(Customer cutomer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            cutomer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            cutomer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            cutomer.setEmail(request.email());
        }
        if(request.address()!=null){
            cutomer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return  repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId).map(mapper::fromCustomer).orElseThrow(()-> new CustomerNotFoundException(
                String.format("cannot update the customer with id:: %s",customerId)));
    }

    public void deleteById(String customerId) {
        repository.deleteById(customerId);
    }
}
