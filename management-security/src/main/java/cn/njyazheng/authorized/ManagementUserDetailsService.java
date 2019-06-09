package cn.njyazheng.authorized;

import cn.njyazheng.domain.Role;
import cn.njyazheng.domain.User;
import cn.njyazheng.service.RoleService;
import cn.njyazheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class ManagementUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        String password=user.getPassword();
        boolean enbled=user.getStatus()==User.USER_STATUS_NORMAL;
        boolean accountNonLocked=user.getLocked()==User.USER_UNLOCKED;
        List<GrantedAuthority> grantedAuthorities=roleService.getRolesByUsername(username).stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(username, password,enbled,
        true,true, accountNonLocked,grantedAuthorities );
    }
    
}
