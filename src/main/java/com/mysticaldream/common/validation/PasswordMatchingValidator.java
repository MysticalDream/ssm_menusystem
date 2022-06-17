package com.mysticaldream.common.validation;

import com.mysticaldream.web.annotation.PasswordMatching;
import com.mysticaldream.web.vo.LoginUserVO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 匹配密码是否相等
 *
 * @description: PasswordMatchingValidator
 * @date: 2022/6/5 16:24
 * @author: MysticalDream
 */
public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {

    @Override
    public void initialize(PasswordMatching constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if ((obj instanceof LoginUserVO loginUserVO)) {
            return loginUserVO.getPassword().equals(loginUserVO.getConfirmPassword());
        }
        return true;
    }
}
