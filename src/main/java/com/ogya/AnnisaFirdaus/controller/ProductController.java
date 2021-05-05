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

import com.ogya.AnnisaFirdaus.domain.Product;
import com.ogya.AnnisaFirdaus.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ProductController {
    private static final String String = null;
	@Autowired
    private ProductRepository productRepository;

    @GetMapping("/list-product")
    public String main(@RequestParam(required = false, defaultValue = "") String search,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Product> products;

        if (search != null && !search.isEmpty()) {
            products = productRepository.findByName(search, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        model.addAttribute("products", products);
        model.addAttribute("search", search);

        return "product/list-product";
    }
#test


    @GetMapping("/add-product")
    public String showSignUpForm(Product product) {
        return "product/add-product";
    }

    @PostMapping("/addproduct")
    public String addProduct(@Validated Product product, BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
            return "product/add-product";
        }

        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll(pageable));
        return "redirect:/list-product";

    }

    // additional CRUD methods
    @GetMapping("/edit-product/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));

        model.addAttribute("product", product);
        return "product/update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") long id, @Validated Product product,
                             BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
        	product.setId(id);
            return "product/update-product";
        }

        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll(pageable));
        return "redirect:/list-product";
    }
    
    @GetMapping("/delete-product/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model, Pageable pageable) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
        productRepository.delete(product);
        model.addAttribute("products", productRepository.findAll(pageable));
        return "redirect:/list-product";
 }

    @GetMapping("/filter1")
    public String filterProducts(@RequestParam(value = "filter", required = false, defaultValue = "") String filterBrand,
                            Model model,
                            @PageableDefault(sort = {"brand"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Product> products;

        if (filterBrand != null && !filterBrand.isEmpty()) {
            products = productRepository.findByBrand(filterBrand, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        model.addAttribute("products", products);
        model.addAttribute("filterBrand", filterBrand);
        

        return "product/list-product";
    }
    
    @GetMapping("/filter2")
    public String filterProducts2(@RequestParam(value = "filter", required = false, defaultValue = "") String filterMadein,
                            Model model,
                            @PageableDefault(sort = {"madein"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Product> products;

        if (filterMadein != null && !filterMadein.isEmpty()) {
            products = productRepository.findByMadein(filterMadein, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        model.addAttribute("products", products);
        model.addAttribute("filterMadein", filterMadein);
        

        return "product/list-product";
    }


}
