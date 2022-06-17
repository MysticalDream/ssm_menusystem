package com.mysticaldream;

import com.mysticaldream.common.constant.ProjectVariables;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Map;

/**
 * 启动类
 *
 * @description: SSMApplication
 * @date: 2022/5/23 19:23
 * @author: MysticalDream
 */
public class SSMApplication {

    private static int port = 80;

    public static void main(String[] args) throws LifecycleException {
        init();
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(port);

        System.out.println("工作目录: " + tomcat.getServer().getCatalinaBase().getAbsolutePath());
        // Tomcat 9.0 必须调用 Tomcat#getConnector() 方法之后才会监听端口,本次使用的是tomcat8
        Connector connector = tomcat.getConnector();
        System.out.println("连接器设置完成: " + connector);
        System.out.println("webapp位置:" + new File("src/main/webapp").getAbsolutePath());

        Context ctx = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());


        WebResourceRoot resources = new StandardRoot(ctx);

        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));

        ctx.setResources(resources);
        tomcat.start();
        System.out.println("tomcat 已启动");
        tomcat.getServer().await();
    }

    public static void init() {
        Yaml yaml = new Yaml();
        Map load = yaml.load(ClassLoader.getSystemResourceAsStream("application.yml"));
        System.setProperty("log4j.basePath", (String) ((Map) load.get("log4j")).get("basePath"));
        Map ssmServer = (Map) load.get("ssmServer");
        port = (Integer) ssmServer.get("port");
        ProjectVariables.setROOT((String) ssmServer.get("imgLocation"));
    }
}
