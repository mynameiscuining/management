package cn.njyazheng.authorized;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component
public class RBACService {
    private AntPathMatcher antPathMatcher=new AntPathMatcher();
    public boolean hasPermission(HttpServletRequest servletRequest, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        
        if(principal instanceof UserDetails){
            String name=((UserDetails)principal).getUsername();
            //根据用户名获取所有的可访问url
            Set<String> urls=new HashSet<>();
            if(urls.stream().filter(url->antPathMatcher.match(url,servletRequest.getRequestURI())).count()>0L){
                return true;
            }
            
        }
        return false;
    }
}
