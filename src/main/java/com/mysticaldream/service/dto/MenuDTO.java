package com.mysticaldream.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: MenuDTO
 * @date: 2022/6/16 17:57
 * @author: MysticalDream
 */
@Data
public class MenuDTO implements Serializable {
    /**
     * 菜谱id
     */
    private Long id;

    /**
     * 菜谱名
     */
    private String menuName;

    /**
     * 菜谱介绍
     */
    private String intro;

    /**
     * 菜谱图片地址
     */
    private String coverUrl;

    /**
     * 菜谱分类
     */
    private String category;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;

    /**
     * 创建用户id
     */
    private String userName;

    /**
     * 总分数
     */
    private BigDecimal score;


    private static final long serialVersionUID = 1L;
}
