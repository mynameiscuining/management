package cn.njyazheng.authorized;


import cn.njyazheng.domain.Permission;
import cn.njyazheng.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RBACService {
    private AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Autowired
    private PermissionService permissionService;
    public boolean hasPermission(HttpServletRequest servletRequest, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        
        if(principal instanceof UserDetails){
            String name=((UserDetails)principal).getUsername();
            //根据用户名获取所有的可访问url
            Set<String> urls=permissionService.getPermissionsByUsername(name).stream().map(Permission::getUrl).collect(Collectors.toSet());
            if(urls.stream().filter(url->antPathMatcher.match(url,servletRequest.getRequestURI())).count()>0L){
                return true;
            }
        }
        return false;
    }
}
