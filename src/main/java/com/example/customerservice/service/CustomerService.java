package com.example.customerservice.service;


	
	import java.time.LocalDateTime;
	import java.util.List;

	import org.springframework.stereotype.Service;

	import com.example.customerservice.model.Customer;
	import com.example.customerservice.repository.CustomerRepository;

	@Service
	public class CustomerService {

	    private final CustomerRepository customerRepository;

	    public CustomerService(CustomerRepository customerRepository) {
	        this.customerRepository = customerRepository;
	    }

	    public Customer createCustomer(Customer customer) {

	        customer.setStatus("ACTIVE");
	        customer.setCreatedAt(LocalDateTime.now());
	        customer.setUpdatedAt(LocalDateTime.now());

	        return customerRepository.save(customer);
	    }

	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }

	    public Customer getCustomerById(Long id) {
	        return customerRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Customer not found"));
	    }

	    public Customer updateCustomer(Long id, Customer customer) {

	        Customer existingCustomer = getCustomerById(id);

	        existingCustomer.setFirstName(customer.getFirstName());
	        existingCustomer.setLastName(customer.getLastName());
	        existingCustomer.setEmail(customer.getEmail());
	        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
	        existingCustomer.setDateOfBirth(customer.getDateOfBirth());
	        existingCustomer.setUpdatedAt(LocalDateTime.now());

	        return customerRepository.save(existingCustomer);
	    }

	    public void deleteCustomer(Long id) {
	        customerRepository.deleteById(id);
	    }
	}

