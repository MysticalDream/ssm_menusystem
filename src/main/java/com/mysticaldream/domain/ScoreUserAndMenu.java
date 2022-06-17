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
 * @description: ScoreUserAndMenu 
 * @date: 2022/5/28 23:02 
 * @author: MysticalDream 
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScoreUserAndMenu implements Serializable {
    /**
    * id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long uid;

    /**
    * 菜单id
    */
    private Long menuId;

    /**
    * 分数
    */
    private BigDecimal score;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 最后更新时间
    */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}