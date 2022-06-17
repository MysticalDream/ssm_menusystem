package com.mysticaldream.common.other;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * @description: YamlTest
 * @date: 2022/6/17 15:25
 * @author: MysticalDream
 */
public class YamlTest {
    @Test
    public void test() {
        Yaml yaml=new Yaml();
        Map load = (Map) yaml.load(ClassLoader.getSystemResourceAsStream("application.yml"));
        System.out.println(load);
        Map ssmServer = (Map) load.get("ssmServer");
        System.out.println(ssmServer.get("imgLocation").getClass().getName());

    }
}
