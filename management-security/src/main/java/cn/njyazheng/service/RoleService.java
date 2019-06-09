package cn.njyazheng.service;

import cn.njyazheng.dao.RoleDao;
import cn.njyazheng.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public List<Role>getRolesByUsername(String name){
       return roleDao.getRolesByUsername(name);
    }
}
