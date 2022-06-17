package com.mysticaldream.common.utils;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 *
 * @description: IpUtilsTest
 * @date: 2022/5/29 16:00
 * @author: MysticalDream
 */
public class IpUtilsTest {

    @Test
    public void getIpAddr() {

    }

    @Test
    public void getHostIp() {
        String hostIp = IpUtils.getHostIp();
        System.out.println(hostIp);
    }

    @Test
    public void getHostName() {
        String hostName = IpUtils.getHostName();
        System.out.println(hostName);
    }

    @Test
    public void check() {
        boolean b = IpUtils.internalIp("10.201.13.149");
        System.out.println(b);
    }
}