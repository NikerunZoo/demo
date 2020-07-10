package cn.itcast.service.client;

import cn.itcast.service.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author NikerunZoo
 * @date 2020/7/9 0009 17:15
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setUsername("服务器正忙，请稍后再试");
        return user;
    }
}
