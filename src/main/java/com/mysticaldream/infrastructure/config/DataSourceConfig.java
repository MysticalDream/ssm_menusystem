package com.mysticaldream.infrastructure.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @description: DataSourceConfig
 * @date: 2022/5/20 20:47
 * @author: MysticalDream
 */
@Configuration
@PropertySource({"classpath:jdbc.properties", "classpath:db.validation.properties"})
public class DataSourceConfig {


    private Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }


    /**
     * HikariCP数据源配置
     *
     * @return
     */
    @Bean
    public HikariConfig createHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(environment.getProperty("jdbc.dataSourceClassName"));
        config.setPoolName("springHikariCP");
        config.setConnectionTestQuery(environment.getProperty("com.mysql.cj.jdbc.Driver"));
        config.addDataSourceProperty("url", environment.getProperty("jdbc.url"));
        config.addDataSourceProperty("user", environment.getProperty("jdbc.username"));
        config.addDataSourceProperty("password", environment.getProperty("jdbc.password"));
        config.addDataSourceProperty("cachePrepStmts", environment.getProperty("dataSource.cachePrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", environment.getProperty("dataSource.prepStmtCacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", environment.getProperty("dataSource.prepStmtCacheSqlLimit"));
        config.addDataSourceProperty("useServerPrepStmts", environment.getProperty("dataSource.useServerPrepStmts"));
        config.addDataSourceProperty("useLocalSessionState", environment.getProperty("dataSource.useLocalSessionState"));
        config.addDataSourceProperty("rewriteBatchedStatements", environment.getProperty("dataSource.rewriteBatchedStatements"));
        config.addDataSourceProperty("cacheResultSetMetadata", environment.getProperty("dataSource.cacheResultSetMetadata"));
        config.addDataSourceProperty("cacheServerConfiguration", environment.getProperty("dataSource.cacheServerConfiguration"));
        config.addDataSourceProperty("elideSetAutoCommits", environment.getProperty("dataSource.elideSetAutoCommits"));
        config.addDataSourceProperty("maintainTimeStats", environment.getProperty("dataSource.maintainTimeStats"));
        return config;
    }

    /**
     * 创建数据源
     *
     * @param config
     * @return
     */
    @Bean
    public DataSource createDataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }


}
