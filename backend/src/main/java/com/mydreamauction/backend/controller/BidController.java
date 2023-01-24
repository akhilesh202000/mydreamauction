package com.mydreamauction.backend.controller;

import com.mydreamauction.backend.dao.BidDao;
import com.mydreamauction.backend.model.DAOBid;
import com.mydreamauction.backend.model.DAOProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class BidController {

    @Autowired
    private final BidDao bidsRepository;

    public BidController(BidDao bidsRepository) {
        this.bidsRepository = bidsRepository;
    }


    @GetMapping("/bids")
    public @ResponseBody Iterable<DAOBid> getBids() {
        return bidsRepository.findAll();
    }

    @GetMapping("/bids/{name}")
    public @ResponseBody Iterable<DAOBid> getBidsUser(@PathVariable String name) {
        List<DAOBid> tmp = new LinkedList<>();

        List<DAOBid> userList = (List<DAOBid>) bidsRepository.findAll();
        for (DAOBid bid : userList) {
            if (bid.getName().equals(name)) {
                tmp.add(bid);
            }
        }
        return tmp;
    }

    @PostMapping("/bids")
    public @ResponseBody DAOBid postBid(@RequestBody DAOBid newBid) {
        return bidsRepository.save(newBid);
    }

    @PatchMapping("/bids/{id}")
    public ResponseEntity<?> confirmBid(@PathVariable Long id, @RequestBody DAOBid x) throws Exception {
        DAOBid tmp = null;
        List<DAOBid> userList = (List<DAOBid>) bidsRepository.findAll();
        for (DAOBid bid : userList) {
            if (bid.getId().equals(id)) {
                tmp = bid;
            }
        }
        if (tmp != null) {
            tmp.setName(tmp.getName());
            tmp.setDeliverydate(tmp.getDeliverydate());
            tmp.setPrice(tmp.getPrice());
            tmp.setQuantity(tmp.getQuantity());
            tmp.setAccepted(x.isAccepted());

            return ResponseEntity.ok(bidsRepository.save(tmp));
        } else return null;
    }

    @DeleteMapping("/bids/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws Exception {
        try {
            DAOBid tmp = null;
            List<DAOBid> userList = (List<DAOBid>) bidsRepository.findAll();
            for (DAOBid bid : userList) {
                if (bid.getId().equals(id)) tmp = bid;
            }
            if (tmp != null) {
                bidsRepository.delete(tmp);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
