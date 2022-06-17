package com.mysticaldream.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: BaseEntity
 * @date: 2022/6/1 16:57
 * @author: MysticalDream
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
