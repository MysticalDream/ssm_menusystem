package com.mysticaldream.infrastructure.config;

import com.mysticaldream.common.constant.ProjectVariables;
import com.mysticaldream.common.jackson.serializer.CustomLongSerializer;
import com.mysticaldream.infrastructure.web.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @description: AppConfig
 * @date: 2022/5/17 11:25
 * @author: MysticalDream
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.mysticaldream.web.controller")
public class WebMvcConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/menusystem/resources/**").addResourceLocations("/WEB-INF/static/");
        registry.addResourceHandler("/resources/**").addResourceLocations("file:"+ ProjectVariables.ROOT+"/");
    }

    /**
     * 配置文件上传解析器
     */
    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    //配置消息转换
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //解决乱码，设置默认字符集为UTF-8
        StringHttpMessageConverter stringHttpMessageConverter =
                new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converters.add(stringHttpMessageConverter);
        //jackson解析
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
        if (this.applicationContext != null) {
            builder.applicationContext(this.applicationContext);
        }
        builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        builder.serializerByType(Long.class, new CustomLongSerializer());

        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(builder.build());

        converters.add(jackson2HttpMessageConverter);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/register", "/menusystem/resources/**");
    }


}
