package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Customer;
import it.uniroma3.repository.CustomerRepository;

@Transactional
@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	public Customer save(Customer customer) {
		return this.customerRepository.save(customer);
	}

	public List<Customer> findByCity(String city) {
		return this.customerRepository.findByCity(city);
	}

	public List<Customer> findAll() {
		return (List<Customer>) this.customerRepository.findAll();
	}
	
	public Customer findById(Long id) {
		Optional<Customer> customer = this.customerRepository.findById(id);
		if (customer.isPresent()) 
			return customer.get();
		else
			return null;
	}

	public boolean alreadyExists(Customer customer) {
		List<Customer> customers = this.customerRepository.findByNameAndSurnameAndCity(customer.getName(), customer.getSurname(), customer.getCity());
		if (customers.size() > 0)
			return true;
		else 
			return false;
	}	
}
