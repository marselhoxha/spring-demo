package it.uniroma3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.CustomerValidator;
import it.uniroma3.model.Customer;
import it.uniroma3.service.CustomerService;

@Controller
public class CustomerController {
	
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidator validator;

    @RequestMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", this.customerService.findAll());
        return "customerList";
    }

    @RequestMapping("/addCustomer")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public String getCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", this.customerService.findById(id));
    	return "showCustomer";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String newCustomer(@Valid @ModelAttribute("customer") Customer customer, 
    									Model model, BindingResult bindingResult) {
        this.validator.validate(customer, bindingResult);
        
        if (this.customerService.alreadyExists(customer)) {
            model.addAttribute("exists", "Customer already exists");
            return "customerForm";
        }
        else {
            if (!bindingResult.hasErrors()) {
                this.customerService.save(customer);
                model.addAttribute("customers", this.customerService.findAll());
                return "customerList";
            }
        }
        return "customerForm";
    }

}
