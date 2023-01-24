package com.mydreamauction.backend.controller;

import com.mydreamauction.backend.dao.AuctionDao;
import com.mydreamauction.backend.model.DAOAuction;
import com.mydreamauction.backend.model.DAOBid;
import com.mydreamauction.backend.model.DAOProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin

public class AuctionController {
    @Autowired
    private final AuctionDao auctionRepository;

    public AuctionController(AuctionDao auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @CrossOrigin
    @GetMapping("/auctions")
    public @ResponseBody Iterable<DAOAuction> getAuctions() {
        return auctionRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/auctions/{id}")
    public @ResponseBody DAOAuction getAuctions(@PathVariable Long id) {
        DAOAuction tmp = null;
        List<DAOAuction> userList = (List<DAOAuction>) auctionRepository.findAll();
        for (DAOAuction auction : userList) {
            if (auction.getId().equals(id)) {
                tmp = auction;
            }
        }
        if (tmp != null) {
            return tmp;
        } else return null;
    }

    @CrossOrigin
    @GetMapping("/auctions/offers/{username}")
    public @ResponseBody List<DAOAuction> getAuctions(@PathVariable String username) {
        List<DAOAuction> tmp = new LinkedList<>();
        List<DAOAuction> userList = (List<DAOAuction>) auctionRepository.findAll();
        for (DAOAuction auction : userList) {
            if (auction.getUsername().equals(username)) {
                tmp.add(auction);
            }
        }
        return tmp;
    }

    @CrossOrigin
    @PostMapping("/auctions")
    public @ResponseBody DAOAuction postAuction(@RequestBody @Valid DAOAuction newAuction) {
        return auctionRepository.save(newAuction);
    }

    @CrossOrigin
    @PatchMapping("/auctions/{id}")
    public ResponseEntity<?> updateAuction(@PathVariable Long id, @RequestBody DAOAuction x) throws Exception {
        DAOAuction tmp = null;
        List<DAOAuction> userList = (List<DAOAuction>) auctionRepository.findAll();
        for (DAOAuction auction : userList) {
            if (auction.getId().equals(id)) {
                tmp = auction;
            }
        }
        if (tmp != null) {
            tmp.setName(tmp.getName());
            tmp.setCategory(tmp.getCategory());
            tmp.setProducts(tmp.getProducts());
            tmp.setStartDate(tmp.getStartDate());
            tmp.setEndDate(tmp.getEndDate());
            tmp.setUsername(tmp.getUsername());
            tmp.setCurrHighestPrice(tmp.getCurrHighestPrice());
            tmp.setLocked(tmp.isLocked());
            // only change products
            List<DAOProduct> list = tmp.getProducts();
            list.add(x.getProducts().get(0));
            tmp.setProducts(list);

            return ResponseEntity.ok(auctionRepository.save(tmp));
        } else return null;
    }

    @CrossOrigin
    @PatchMapping("/auctions/bids/lock/{id}")
    public ResponseEntity<?> updateAuctionBidStatus(@PathVariable Long id, @RequestBody DAOAuction x) throws Exception {
        DAOAuction tmp = null;
        List<DAOAuction> userList = (List<DAOAuction>) auctionRepository.findAll();
        for (DAOAuction auction : userList) {
            if (auction.getId().equals(id)) {
                tmp = auction;
            }
        }
        if (tmp != null) {
            tmp.setName(tmp.getName());
            tmp.setCategory(tmp.getCategory());
            tmp.setProducts(tmp.getProducts());
            tmp.setStartDate(tmp.getStartDate());
            tmp.setEndDate(tmp.getEndDate());
            tmp.setProducts(tmp.getProducts());
            tmp.setCurrHighestPrice(tmp.getCurrHighestPrice());
            tmp.setUsername(tmp.getUsername());
            tmp.setLocked(x.isLocked());

            return ResponseEntity.ok(auctionRepository.save(tmp));
        } else return null;
    }

    @CrossOrigin
    @GetMapping("/auctions/bids/highest/{id}")
    public int updateAuctionBidHighest(@PathVariable Long id) throws Exception {
        DAOAuction tmp = null;
        List<DAOAuction> userList = (List<DAOAuction>) auctionRepository.findAll();

        int max = 0;
        for (DAOAuction x : userList
        ) {
            if (x.getProducts().size() != 0) {
                for (DAOProduct p : x.getProducts()
                ) {
                    if (p.getBids().size() != 0) {
                        for (DAOBid b : p.getBids()
                        ) {
                            max = Math.max(Integer.parseInt(b.getPrice()), max);
                        }
                    }
                }
            }
        }
        return max;
    }
}
