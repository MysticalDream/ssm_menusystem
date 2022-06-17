package com.mysticaldream.common.annotation;

import java.lang.annotation.*;

/**
 * 表明不需要扫描此类
 *
 * @description: IgnoreScan
 * @date: 2022/5/21 22:56
 * @author: MysticalDream
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IgnoreScan {

}
