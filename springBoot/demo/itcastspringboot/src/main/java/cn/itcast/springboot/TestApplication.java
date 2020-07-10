package cn.itcast.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author NikerunZoo
 * @date 2020/7/7 0007 16:45
 */
//@EnableAutoConfiguration//启动自动配置
//@ComponentScan //类似于<context:component-scan base-package=>
@SpringBootApplication
public class TestApplication {//称为引导类，是spring应用程序的入口

    public static void main(String[] args) {

        SpringApplication.run(TestApplication.class ,args);
    }
}
