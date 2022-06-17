package com.mysticaldream.infrastructure.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description: MyBatisConfig
 * @date: 2022/5/20 20:00
 * @author: MysticalDream
 */
@Configuration
public class MyBatisConfig {


    private final Environment environment;

    public MyBatisConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, PageInterceptor pageInterceptor) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver path = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(path.getResources("classpath*:com/mysticaldream/mapper/**/*.xml"));
//        sqlSessionFactoryBean.setTypeAliasesPackage("com/mysticaldream/domain");
        sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("mybatis/mybatis-config.xml"));
        //分页插件
        Interceptor[] interceptors = {pageInterceptor};
        sqlSessionFactoryBean.setPlugins(interceptors);
        return sqlSessionFactoryBean;
    }

    @Bean
    PlatformTransactionManager createTxManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 分页插件
     */
    @Bean
    public PageInterceptor paginationInterceptor() {

        PageInterceptor pageInterceptor = new PageInterceptor();

        Properties properties = new Properties();
        properties.setProperty("helperDialect", environment.getProperty("pagehelper.helperDialect"));
        properties.setProperty("reasonable", environment.getProperty("pagehelper.reasonable"));
        properties.setProperty("supportMethodsArguments", environment.getProperty("pagehelper.supportMethodsArguments"));

        pageInterceptor.setProperties(properties);

        return pageInterceptor;
    }


}
