package com.boketto.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:  比价商品类目新增参实体
 * @projectName: Muyuan-Biz-shiye-sms
 * @packageName: org.commodity.param
 * @author: llf
 * @createTime: 2022/11/7 17:22
 * @version: 1.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SmsCategoryInsertParam implements Serializable {

    private static final long serialVersionUID = -2758108375048089903L;

    /**
     * 类目编码
     */
    private String categoryCode;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目级别 1：一级类目 2：二级类目 3：三级类目
     */
    private Integer categoryLevel;

    /**
     * 父级类目编码(一級类目，父级传值0)
     */
    private String parentCode;
}
