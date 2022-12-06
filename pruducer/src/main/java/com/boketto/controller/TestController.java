package com.boketto.controller;

import com.boketto.param.SmsCommodityInsertParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author llf
 * @Title:
 * @Package
 * @Description:
 * @date 2022/12/2 0:58
 */
@RestController
public class TestController {

    /**
     * @Description: 批量添加比价商品
     * @Author: llf
     * @Date: 2022/12/2 16:07
     * @param commodityList: 商品集合
     * @return: org.muyuan.core.tool.api.R<java.lang.String>
     **/
    @PostMapping("/batchInsertCommodity")
    public String batchInsertCommodity( @RequestBody List<SmsCommodityInsertParam> commodityList){
        return "年后";
    }


}
