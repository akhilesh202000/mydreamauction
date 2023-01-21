package com.mydreamauction.backend.controller;

import com.mydreamauction.backend.dao.ProductDao;
import com.mydreamauction.backend.model.DAOProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    private final ProductDao productRepository;

    public ProductController(ProductDao productRepository) {
        this.productRepository = productRepository;
    }
    @CrossOrigin
    @GetMapping("/products")
    public @ResponseBody Iterable<DAOProduct> getAuctions() {
        return productRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/products")
    public @ResponseBody DAOProduct postAuction(@RequestBody @Valid DAOProduct newProduct) {
        return productRepository.save(newProduct);
    }
}