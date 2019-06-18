package cn.njyazheng.service;

import cn.njyazheng.dao.PermissionDao;
import cn.njyazheng.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    private AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Autowired
    private PermissionDao permissionDao;
    public List<Permission> getPermissionsByUsername(String username){
       return permissionDao.getPermissionsByUsername(username);
    }
    
    public String getPermissionTypes(String name,String url){
        
        List<Permission> permissions =permissionDao.getPermissionsByUsername(name);
        Permission permission=permissions.stream().filter(p-> Objects.nonNull(p.getUrl())).filter(p->antPathMatcher.match(url,p.getUrl())).findFirst().get();
        return permissions.stream().filter(p->p.getParentid().equals(permission.getId())).map(p->p.getId().toString()).collect(Collectors.joining(","));
    }
}
