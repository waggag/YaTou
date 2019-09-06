package cn.waggag.yatou.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @description: Druid的配置类
 * @author: waggag
 * @time: 2019/9/7
 * @Company http://www.waggag.cn
 */
@Configuration
public class DruidConfiguration {

    @ConfigurationProperties(prefix = "spring.database")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

}
