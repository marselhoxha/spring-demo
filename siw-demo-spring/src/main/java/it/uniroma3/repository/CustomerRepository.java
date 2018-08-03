package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public List<Customer> findByCity(String city);

	public List<Customer> findByNameAndSurnameAndCity(String name, String surname, String city);

}
