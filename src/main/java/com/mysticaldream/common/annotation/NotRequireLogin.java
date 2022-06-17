package com.mysticaldream.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义不需要登录注解，只能用于方法
 *
 * @description: NotRequireLogin
 * @date: 2022/6/3 15:11
 * @author: MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface NotRequireLogin {

}
