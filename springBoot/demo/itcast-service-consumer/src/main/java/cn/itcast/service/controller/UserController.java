package cn.itcast.service.controller;

import cn.itcast.service.client.UserClient;
import cn.itcast.service.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author NikerunZoo
 * @date 2020/7/8 0008 17:23
 */
@RestController
@RequestMapping("consumer/user")
//@DefaultProperties(defaultFallback = "fallback")
public class UserController {
   /* @Autowired
    private RestTemplate restTemplate;*/
  /*  @Autowired
    private DiscoveryClient discoveryClient;//服务地址列表*/
  @Autowired
  private UserClient userClient;

    @GetMapping
    @HystrixCommand
    public String  queryUserById(@RequestParam("id") Integer id){
        //实现熔断
       /* if (id==41){
            throw new RuntimeException();
        }*/

        //通过服务的id获取服务实例的集合
      /*  List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);

        return this.restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id,User.class);*/
      //使用ribbon实现负载均衡
//        return this.restTemplate.getForObject("http://service-provider/user/"+id,String.class);

        //使用feign组件自动实现rest远程调用
        return this.userClient.queryUserById(id).toString();
    }

   /* public String fallback(){
        return "服务器正忙，请稍后再试.fallback";
    }
    public String queryUserByIdFallback(Integer id){
        return "服务器正忙，请稍后再试";
    }*/
}
