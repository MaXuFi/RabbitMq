package com.boketto.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description:  比价商品新增参实体
 * @projectName: Muyuan-Biz-shiye-sms
 * @packageName: org.commodity.param
 * @author: llf
 * @createTime: 2022/11/7 17:22
 * @version: 1.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SmsCommodityInsertParam implements Serializable {


    private static final long serialVersionUID = -6113618433762476637L;
    /**
     * @Description: 商品编码
     **/
    private String spuCode;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品品牌
     */
    private String productBrand;

    /**
     * 最高价
     */
    private BigDecimal highestPrice;

    /**
     * 最低价
     */
    private BigDecimal minimumPrice;

    /**
     * 商品图地址
     */
    private String productImage;

    /**
     * 类目集合
     */
    List<SmsCategoryInsertParam> categoryList;
}
