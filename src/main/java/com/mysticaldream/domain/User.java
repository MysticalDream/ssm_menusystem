package com.mysticaldream.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @description: User
 * @date: 2022/5/28 23:01
 * @author: MysticalDream
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 32, message = "用户名长度范围是1-32位")
    @Pattern(regexp = "(?=^\\w+$)(.+)",message = "用户名只能由由数字、26个英文字母或者下划线组成")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 20, message = "密码长度范围是8-20位")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(.+)",message = "密码必须包含大小写字母和数字的组合，不能使用特殊字符")
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户角色
     */
    private String role;


    /**
     * 最后登录ip
     */
    private String loginIp;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

}