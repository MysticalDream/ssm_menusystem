package com.mysticaldream.service.impl;

import com.mysticaldream.infrastructure.config.RootConfig;
import com.mysticaldream.web.vo.LoginUserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: ValidatorTest
 * @date: 2022/6/5 21:10
 * @author: MysticalDream
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class ValidatorTest {

    @Autowired
    Validator validator;

    @Test
    public void test() {
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setUsername("abc");
        loginUserVO.setPassword("Javabb123");
        loginUserVO.setConfirmPassword("Javacc123");
        Set<ConstraintViolation<LoginUserVO>> validate = validator.validate(loginUserVO);
        validate.forEach(v -> {
            System.out.println(v.getMessage() + "." + v.getInvalidValue());
        });
    }

    @Test
    public void test2() {
        Pattern pattern=Pattern.compile("(?=^\\w+$)(.+)");
        Matcher matcher = pattern.matcher("abc");
        boolean matches = matcher.matches();
        System.out.println(matches);

    }
}
