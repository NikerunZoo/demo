package cn.itcast.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author NikerunZoo
 * @date 2020/7/7 0007 17:21
 *
 */
@Configuration//声明一个类是java配置类，相当于xml配置
//@PropertySource("classpath:jdbc.properties")//读取外部资源文件
@EnableConfigurationProperties(JdbcProperties.class)//启动资源配置读取类
public class JdbcConfiguration {
    /*
    * 四种注入方式
    *   1.Autowired
    *   2.构造方法注入
    *   3.方法的形参注入
    *   4.将@ConfigurationProperties(prefix = "jdbc")放在指定方法上,则不需要资源配置文件了
     *   */
    // 1.Autowired
//    @Autowired
//    private JdbcProperties jdbcProperties;

    //2.构造方法注入
    /*public JdbcConfiguration(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }*/

    @Bean//把方法的返回值注入spring容器
                                //3.方法的形参注入
    public DataSource dataSource(JdbcProperties jdbcProperties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        return  dataSource;
    }



   /* @Bean//把方法的返回值注入spring容器
    //4.将@ConfigurationProperties(prefix = "jdbc")放在指定方法上,则不需要资源配置文件了
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource(JdbcProperties jdbcProperties){
        DruidDataSource dataSource = new DruidDataSource();
        return  dataSource;
    }*/
}
