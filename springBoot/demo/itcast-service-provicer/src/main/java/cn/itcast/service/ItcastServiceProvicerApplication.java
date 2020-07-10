package cn.itcast.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient//开启eureka客户端，@EnableEurakaClient
@MapperScan("cn.itcast.service.mapper")//开启mapper接口扫描
public class ItcastServiceProvicerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItcastServiceProvicerApplication.class, args);
	}

}
