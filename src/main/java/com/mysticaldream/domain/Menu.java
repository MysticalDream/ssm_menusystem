package com.mysticaldream.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: Menu 
 * @date: 2022/5/28 22:55 
 * @author: MysticalDream 
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
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
    private Integer cid;

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
    private Long createUid;

    /**
    * 最后修改的用户id
    */
    private Long updateUid;

    /**
    * 是否被删除
    */
    private Boolean isDeleted;

    /**
    * 总分数
    */
    private BigDecimal score;

    /**
    * 总打分人数
    */
    private Integer scoreCount;

    private static final long serialVersionUID = 1L;
}