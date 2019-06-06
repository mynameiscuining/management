package cn.njyazheng.config;

import cn.njyazheng.auth.AuthenticationConfiguration;
import cn.njyazheng.code.verify.auth.VerifyCodeAuthenticationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
public class WebSecurityConfiguration extends AuthenticationConfiguration {
   
    @Autowired
    private VerifyCodeAuthenticationConfiguration verifyCodeAuthenticationConfiguration;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单,认证
        configureAuthentication(http);
        //跨域解禁
        http.csrf().disable();
        //iframe嵌入网页解禁
        http.headers().frameOptions().disable();
        
        //验证码拦截器
        http.apply(verifyCodeAuthenticationConfiguration);
        
    }
}
