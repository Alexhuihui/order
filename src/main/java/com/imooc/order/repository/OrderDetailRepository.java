package com.imooc.order.repository;

import com.imooc.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 汪永晖
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
