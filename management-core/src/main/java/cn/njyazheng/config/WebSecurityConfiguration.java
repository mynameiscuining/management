package cn.njyazheng.config;

import cn.njyazheng.authorized.AuthenticationConfiguration;
import cn.njyazheng.code.verify.auth.VerifyCodeAuthenticationConfiguration;
import cn.njyazheng.param.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;


@Configuration
public class WebSecurityConfiguration extends AuthenticationConfiguration {
    
    @Autowired
    private SecurityProperties securityProperties;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(securityProperties.getSecret());
        super.getLogger().info("\"123\" is encrypted :{}",passwordEncoder.encode("123"));
        return passwordEncoder;
    }
    
    @Autowired
    private VerifyCodeAuthenticationConfiguration verifyCodeAuthenticationConfiguration;
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico")
                .antMatchers("/lib/**")
                .antMatchers("/static/**");
    }
    
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
