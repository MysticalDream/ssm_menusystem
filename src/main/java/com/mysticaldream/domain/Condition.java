package com.mysticaldream.domain;

import lombok.Data;

/**
 * 菜谱查询条件
 *
 * @description: Condition
 * @date: 2022/6/15 16:16
 * @author: MysticalDream
 */
@Data
public class Condition {
    private Integer type;
    private Integer id;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String key;
}
