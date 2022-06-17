package com.mysticaldream.common.enums;

import lombok.Getter;

/**
 * @description: Role
 * @date: 2022/6/3 16:12
 * @author: MysticalDream
 */
@Getter
public enum Role {
    /**
     * 普通用户
     */
    USER("user"),
    /**
     * 管理员
     */
    ADMIN("admin");

    String name;

    Role(String name) {
        this.name = name;
    }
}
