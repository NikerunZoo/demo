package cn.itcast.service.service;

import cn.itcast.service.mapper.UserMapper;
import cn.itcast.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author NikerunZoo
 * @date 2020/7/8 0008 17:06
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserById(@PathVariable("id") Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

}
