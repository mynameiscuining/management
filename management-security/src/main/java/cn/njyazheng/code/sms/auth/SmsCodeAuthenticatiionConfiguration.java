package cn.njyazheng.code.sms.auth;

import cn.njyazheng.param.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.*;

import org.springframework.stereotype.Component;

@Component
public class SmsCodeAuthenticatiionConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
   
   
    private AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler=new SimpleUrlAuthenticationFailureHandler("/");

    private SimpleUrlAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler=new SavedRequestAwareAuthenticationSuccessHandler();
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter(securityProperties.getLoginMobile());
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler);
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler);
        
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        
        SmsCodeFilter smsCodeFilter=new SmsCodeFilter();
        smsCodeFilter.setConfigProperties(securityProperties);
        smsCodeFilter.setSimpleUrlAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler);
        smsCodeFilter.afterPropertiesSet();
    
        
        http.authenticationProvider(smsCodeAuthenticationProvider)
                //校验手机验证码
                .addFilterAfter(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                //手机获取用户信息
                .addFilterAfter(smsCodeAuthenticationFilter, SmsCodeFilter.class);
                
                
    }
}
