package com.mysticaldream.service.impl;

import com.mysticaldream.infrastructure.config.RootConfig;
import com.mysticaldream.domain.User;
import com.mysticaldream.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description: UserServiceImplTest
 * @date: 2022/5/29 17:04
 * @author: MysticalDream
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class UserServiceImplTest {

    @Autowired
    UserMapper mapper;

    @Test
    public void getUserById() {
        User user = mapper.selectByPrimaryKey(1L);

        System.out.println(user);
    }
}