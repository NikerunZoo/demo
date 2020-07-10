package cn.itcast.user.service;

import cn.itcast.user.mapper.UserMapper;
import cn.itcast.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author NikerunZoo
 * @date 2020/7/7 0007 20:16
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Integer id){
        return this.userMapper.selectByPrimaryKey(id);
    }

    public List<User>  queryUsers(){
        return  this.userMapper.selectAll();
    }

    @Transactional
    public void  deleteUserById(Integer id){
        this.userMapper.deleteByPrimaryKey(id);
    }

}
