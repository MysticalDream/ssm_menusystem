package com.mysticaldream.web.annotation;

import com.mysticaldream.common.validation.PasswordMatchingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义密码匹配验证
 *
 * @description: PasswordMatching
 * @date: 2022/6/5 16:22
 * @author: MysticalDream
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchingValidator.class)
@Documented
public @interface PasswordMatching {

    String message() default "密码不匹配";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
