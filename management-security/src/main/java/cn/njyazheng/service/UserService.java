package cn.njyazheng.service;

import cn.njyazheng.dao.UserDao;
import cn.njyazheng.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User getUserByUsername(String username){
        List<User> userList =userDao.createLambdaQuery().andEq(User::getUsername,username).select();
        return userList.isEmpty()?null:userList.get(0);
    }
}
