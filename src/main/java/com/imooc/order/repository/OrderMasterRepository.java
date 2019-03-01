package com.imooc.order.repository;

import com.imooc.order.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 汪永晖
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
