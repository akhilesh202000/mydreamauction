package com.mydreamauction.backend.controller;

import com.mydreamauction.backend.dao.ProductDao;
import com.mydreamauction.backend.model.DAOBid;
import com.mydreamauction.backend.model.DAOProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
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
    @GetMapping("/products/{id}")
    public @ResponseBody DAOProduct getAuction(@PathVariable Long id) {
        DAOProduct tmp = null;
        List<DAOProduct> userList = (List<DAOProduct>) productRepository.findAll();
        for (DAOProduct product : userList) {
            if (product.getId().equals(id)) tmp = product;
        }
        return tmp;
    }

    @CrossOrigin
    @PostMapping("/products")
    public @ResponseBody DAOProduct postAuction(@RequestBody @Valid DAOProduct newProduct) {
        return productRepository.save(newProduct);
    }

    @CrossOrigin
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws Exception {
        try {
            DAOProduct tmp = null;
            List<DAOProduct> userList = (List<DAOProduct>) productRepository.findAll();
            for (DAOProduct product : userList) {
                if (product.getId().equals(id)) tmp = product;
            }
            if (tmp != null) {
                productRepository.delete(tmp);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PatchMapping("/products/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody DAOProduct x) throws Exception {
        DAOProduct tmp = null;
        List<DAOProduct> userList = (List<DAOProduct>) productRepository.findAll();
        for (DAOProduct product : userList) {
            if (product.getId().equals(id)) {
                tmp = product;
            }
        }
        if (tmp != null) {
            tmp.setName(x.getName());

            tmp.setDescription(x.getDescription());
            tmp.setBids(tmp.getBids());
            return ResponseEntity.ok(productRepository.save(tmp));
        } else return null;
    }

    @CrossOrigin
    @PatchMapping("/products/bids/{id}")
    public ResponseEntity<?> updateProductBids(@PathVariable Long id, @RequestBody DAOProduct x) throws Exception {
        DAOProduct tmp = null;
        List<DAOProduct> userList = (List<DAOProduct>) productRepository.findAll();
        for (DAOProduct product : userList) {
            if (product.getId().equals(id)) {
                tmp = product;
            }
        }
        if (tmp != null) {
            tmp.setName(tmp.getName());
            tmp.setDescription(tmp.getDescription());

            List<DAOBid> list = tmp.getBids();
            list.add(x.getBids().get(0));
            tmp.setBids(list);
            return ResponseEntity.ok(productRepository.save(tmp));
        } else return null;
    }
}