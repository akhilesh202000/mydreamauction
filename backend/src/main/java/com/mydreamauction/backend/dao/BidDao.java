package com.mydreamauction.backend.dao;

import com.mydreamauction.backend.model.DAOBid;
import org.springframework.data.repository.CrudRepository;

public interface BidDao extends CrudRepository<DAOBid,Integer> {
}
