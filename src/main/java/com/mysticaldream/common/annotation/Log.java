package com.mysticaldream.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义请求参数和响应数据日志记录注解
 *
 * @description: Log
 * @date: 2022/6/2 22:34
 * @author: MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Log {
    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestParam() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}
