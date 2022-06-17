package com.mysticaldream.common.utils;

import com.mysticaldream.domain.User;
import com.mysticaldream.service.dto.UserDTO;
import com.mysticaldream.service.translator.mapstruct.UserTranslator;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/**
 * @description: MapStructTest
 * @date: 2022/5/30 11:16
 * @author: MysticalDream
 */

public class MapStructTest {
    @Test
    public void test() {
        User user = new User();
        user.setUsername("zs");
        user.setPassword("123");
        user.setCreateTime(new Date());
        user.setId(1L);
        user.setAvatar("/path/1.png");
        UserTranslator mapper = Mappers.getMapper(UserTranslator.class);
        UserDTO userDTO = mapper.toTarget(user);
        System.out.println(userDTO);
    }

    @Test
    public void test2() {
        System.out.println("\u007F\u007F");
    }
}
