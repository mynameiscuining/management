package cn.njyazheng.code.verify.auth;

import cn.njyazheng.param.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class VerifyCodeAuthenticationConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    
    private AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler=new SimpleUrlAuthenticationFailureHandler("/");
 
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        VerificationCodeFilter verificationCodeFilter = new VerificationCodeFilter();
        verificationCodeFilter.setSimpleUrlAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler);
        verificationCodeFilter.setConfigProperties(securityProperties);
        //初始化加载信息
        verificationCodeFilter.afterPropertiesSet();
        
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
