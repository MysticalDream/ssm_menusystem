package com.mysticaldream.common.utils;

import com.mysticaldream.infrastructure.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description: IdWorkerUtilTest
 * @date: 2022/6/3 9:40
 * @author: MysticalDream
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class IdWorkerUtilTest {

    @Autowired
    IdWorkerUtil idWorkerUtil;


    @Test
    public void nextId() {
        for (int i = 0; i < 100; i++) {
            long l = idWorkerUtil.nextId();
            System.out.println(l);
        }
    }

    @Test
    public void getMaxWorkerId() {
    }

    @Test
    public void getDatacenterId() {
    }
}