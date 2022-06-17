package com.mysticaldream.common.annotation;

import com.mysticaldream.common.enums.Role;

import java.lang.annotation.*;

/**
 * 自定义登录的注解，可以用于方法和类
 *
 * @description: RequireLogin
 * @date: 2022/6/2 22:07
 * @author: MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface RequireLogin {
    /**
     * 拒绝后跳转的路径
     *
     * @return
     */
    String value() default "/login";

    /**
     * 需要登录的角色
     *
     * @return
     */
    Role[] role() default {Role.USER};


}
