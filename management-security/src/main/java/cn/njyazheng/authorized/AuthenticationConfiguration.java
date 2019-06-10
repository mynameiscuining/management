package cn.njyazheng.authorized;

import cn.njyazheng.param.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Set;


public abstract class  AuthenticationConfiguration extends WebSecurityConfigurerAdapter {
    private  final Logger logger = LoggerFactory.getLogger(AuthenticationConfiguration.class);
    @Autowired
    protected SecurityProperties securityProperties;
    protected void configureAuthentication(HttpSecurity http) throws Exception {
        // 基础认证
        http.httpBasic();
        //表单登录
        http.formLogin()
                .loginPage(securityProperties.getLoginPage())
                .loginProcessingUrl(securityProperties.getLoginForm())
                .defaultSuccessUrl(securityProperties.getDefaultSuccessUrl());
 
        //所有请求要认证
        Set<String> excludePathPatterns = securityProperties.getExcludePathPattern();
        excludePathPatterns.add(securityProperties.getLoginPage());
        excludePathPatterns.add(securityProperties.getLoginForm());
        http.authorizeRequests()
                //不需要认证,比如静态资源
                .antMatchers(excludePathPatterns.toArray(new String[excludePathPatterns.size()])).permitAll()
                .antMatchers("/").authenticated()
                .anyRequest().access("@RBACService.hasPermission(request,authentication)");
        if (logger.isDebugEnabled()) {
            securityProperties.getExcludePathPattern().stream().forEach(s -> {
                logger.debug("authenticated exclude-path-pattern:{} set success", s);
            });
        }
    }
    
    public Logger getLogger() {
        return logger;
    }
}
