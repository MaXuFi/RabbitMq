package com.boketto.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实业销售系统商品表
 * @author Boketto
 * @TableName sms_commodity
 */
@Data
public class SmsCommodity implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品编码(spu)
     */
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
     * 类目层级(类目编码/)
     */
    private String categoryCodeLevel;

    /**
     * 商品图地址
     */
    private String productImage;

    /**
     * 最低价
     */
    private BigDecimal minimumPrice;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 修改人
     */
    private String modifiedId;

    /**
     * 是否有效  0：有效 1：无效
     */
    private Boolean isDeleted;

    /**
     * 版本号
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}
