package it.uniroma3;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.model.Customer;
import it.uniroma3.service.CustomerService;

@SpringBootApplication
public class SiwDemoSpringApplication {

	@Autowired
	private CustomerService customerService; 
	
	public static void main(String[] args) {
		SpringApplication.run(SiwDemoSpringApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		Customer customer = new Customer("Paolo", "Merialdo", "Genova");
		customerService.save(customer);
		for(Customer c : customerService.findByCity("Genova")) {
			System.out.println(c.getName());
		}
	}
}
