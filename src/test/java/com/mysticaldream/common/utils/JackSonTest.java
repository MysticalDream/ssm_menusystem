package com.mysticaldream.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mysticaldream.common.jackson.serializer.CustomLongSerializer;
import com.mysticaldream.domain.User;
import com.mysticaldream.web.vo.ApiResult;
import org.junit.Test;

import java.util.Date;

/**
 * @description: JackSonTest
 * @date: 2022/5/30 8:49
 * @author: MysticalDream
 */
public class JackSonTest {
    @Test
    public void test() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, new CustomLongSerializer());
//        simpleModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        objectMapper.registerModule(simpleModule);

        User user = new User();
        user.setUsername("zs");
        user.setId(1L);
        user.setCreateTime(new Date());
        user.setPassword("12312");
        ApiResult<User> success = ApiResult.success(user);
        boolean b = objectMapper.canSerialize(ApiResult.class);
        System.out.println(b);
        String s = null;
        try {
            s = objectMapper.writer().writeValueAsString(success);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
