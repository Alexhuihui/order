package com.imooc.order.utils;

import com.imooc.order.vo.ResultVO;

/**
 * @author 汪永晖
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}