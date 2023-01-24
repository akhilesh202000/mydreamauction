package com.mydreamauction.backend.dao;

import com.mydreamauction.backend.model.DAOAuction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionDao extends CrudRepository<DAOAuction, Integer> {

}
