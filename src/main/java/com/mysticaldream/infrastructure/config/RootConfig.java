package com.mysticaldream.infrastructure.config;

import com.mysticaldream.common.annotation.IgnoreScan;
import com.mysticaldream.common.utils.IdWorkerUtil;
import com.mysticaldream.infrastructure.config.factory.CompositePropertySourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @description: RootConfig
 * @date: 2022/5/18 9:38
 * @author: MysticalDream
 */
@Configuration
@MapperScan("com.mysticaldream.mapper")
@ComponentScan(value = "com.mysticaldream", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {IgnoreScan.class, Controller.class, EnableWebMvc.class})})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource(value = "classpath:application.yml", factory = CompositePropertySourceFactory.class)
public class RootConfig {


    /**
     * 雪花算法生成id工具
     *
     * @return
     */
    @Bean
    public IdWorkerUtil idWorkerUtil() {
        return new IdWorkerUtil(1, 1);
    }


    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }


}
