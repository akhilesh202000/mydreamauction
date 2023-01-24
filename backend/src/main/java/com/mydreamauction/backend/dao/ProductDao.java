package com.mydreamauction.backend.dao;

import com.mydreamauction.backend.model.DAOProduct;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<DAOProduct, Integer> {
}
