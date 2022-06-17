package com.mysticaldream.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: Category 
 * @date: 2022/5/28 22:59 
 * @author: MysticalDream 
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    /**
    * 菜谱分类id
    */
    private Integer id;

    /**
    * 分类名
    */
    private String name;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;

    /**
    * 是否被删除
    */
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;
}