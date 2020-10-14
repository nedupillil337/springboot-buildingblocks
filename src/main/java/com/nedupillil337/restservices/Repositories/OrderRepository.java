package com.nedupillil337.restservices.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nedupillil337.restservices.Entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

	Order save(Order order);

}
