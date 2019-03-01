package com.imooc.order.vo;

import lombok.Data;

/**
 * @author 汪永晖
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
