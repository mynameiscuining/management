package cn.njyazheng.service;

import cn.njyazheng.dao.PermissionDao;
import cn.njyazheng.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    public List<Permission> getPermissionsByUsername(String username){
       return permissionDao.getPermissionsByUsername(username);
    }
}
