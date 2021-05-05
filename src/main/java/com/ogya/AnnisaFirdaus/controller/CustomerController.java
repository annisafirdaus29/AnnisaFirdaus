package com.ogya.AnnisaFirdaus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ogya.AnnisaFirdaus.domain.Customer;
import com.ogya.AnnisaFirdaus.repository.CustomerRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CustomerController {
    private static final String String = null;
	@Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/list")
    public String main(@RequestParam(required = false, defaultValue = "") String search,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Customer> customers;

        if (search != null && !search.isEmpty()) {
            customers = customerRepository.findByName(search, pageable);
        } else {
            customers = customerRepository.findAll(pageable);
        }

        model.addAttribute("customers", customers);
        model.addAttribute("search", search);

        return "index";
    }


    @GetMapping("/add-customer")
    public String showSignUpForm(Customer customer) {
        return "operations/add-customer";
    }

    @PostMapping("/addcustomer")
    public String addCustomer(@Validated Customer customer, BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
            return "operations/add-customer";
        }

        customerRepository.save(customer);
        model.addAttribute("customers", customerRepository.findAll(pageable));
        return "redirect:/list";

    }

    // additional CRUD methods
    @GetMapping("/edit-customer/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));

        model.addAttribute("customer", customer);
        return "operations/update-customer";
    }

    @PostMapping("/update-customer/{id}")
    public String updateCustomer(@PathVariable("id") long id, @Validated Customer customer,
                             BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
        	customer.setId(id);
            return "operations/update-customer";
        }

        customerRepository.save(customer);
        model.addAttribute("customers", customerRepository.findAll(pageable));
        return "redirect:/list";
    }
    
    @GetMapping("/delete-customer/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model, Pageable pageable) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
        customerRepository.delete(customer);
        model.addAttribute("customers", customerRepository.findAll(pageable));
        return "redirect:/list";
 }

    @GetMapping("/filter")
    public String filterCustomers(@RequestParam(value = "filter", required = false, defaultValue = "") String filterNeeded,
                            Model model,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Customer> customers;

        if (filterNeeded.equals("")) {
            customers = customerRepository.findAll(pageable);
        } else if (filterNeeded.equals("true")) {
            customers = customerRepository.findByGender(true, pageable);
        } else {
            customers = customerRepository.findByGender(false, pageable);
        }

        model.addAttribute("customers", customers);
        model.addAttribute("filterNeeded", filterNeeded);

        return "index";
    }
}
