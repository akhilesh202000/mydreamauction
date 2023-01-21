package com.mydreamauction.backend.controller;

import com.mydreamauction.backend.dao.AuctionDao;
import com.mydreamauction.backend.model.DAOAuction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
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
    @PostMapping("/auctions")
    public @ResponseBody DAOAuction postAuction(@RequestBody @Valid DAOAuction newAuction) {
        return auctionRepository.save(newAuction);
    }
}
