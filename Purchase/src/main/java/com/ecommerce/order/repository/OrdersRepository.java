package com.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
