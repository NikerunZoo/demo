package cn.itcast.service.client;

import cn.itcast.service.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author NikerunZoo
 * @date 2020/7/9 0009 17:02
 */
@FeignClient(value = "service-provider",fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("user/{id}")
    public User queryUserById(@PathVariable("id") Integer id);
}
