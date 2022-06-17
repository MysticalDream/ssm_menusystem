package com.mysticaldream.service.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description: UserDTO
 * @date: 2022/5/30 9:57
 * @author: MysticalDream
 */
@Data
public class UserDTO {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户角色
     */
    private String role;

    /**
     * 创建时间
     */
    private Date createTime;



    private static final long serialVersionUID = 1L;
}
