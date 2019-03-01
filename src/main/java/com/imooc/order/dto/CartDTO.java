package com.imooc.order.dto;

import lombok.Data;

/**
 * @author 汪永晖
 */
@Data
public class CartDTO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品库存
     */
    private Integer productQuantity;

    public CartDTO() {

    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
