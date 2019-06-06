package cn.njyazheng.param;

import cn.njyazheng.code.sms.generate.SmsCodeProperties;
import cn.njyazheng.code.verify.generate.VerificationCodeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "m")
public class SecurityProperties {
    private String loginPage="/login";
    private String loginForm="/auth/form";
    private String defaultSuccessUrl="/";
    private String loginMobile="/auth/mobile";
    private Set<String> excludePathPattern=new HashSet<String>(){{
        add("/favicon.ico");
    }};
    private VerificationCodeProperties verify=new VerificationCodeProperties();
    private SmsCodeProperties sms=new SmsCodeProperties();
    
    public Set<String> getExcludePathPattern() {
        return excludePathPattern;
    }
    
    public void setExcludePathPattern(Set<String> excludePathPattern) {
        this.excludePathPattern = excludePathPattern;
    }
    
    public String getLoginPage() {
        return loginPage;
    }
    
    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
    
    public String getLoginForm() {
        return loginForm;
    }
    
    public void setLoginForm(String loginForm) {
        this.loginForm = loginForm;
    }
    
    public String getDefaultSuccessUrl() {
        return defaultSuccessUrl;
    }
    
    public void setDefaultSuccessUrl(String defaultSuccessUrl) {
        this.defaultSuccessUrl = defaultSuccessUrl;
    }
    
    public VerificationCodeProperties getVerify() {
        return verify;
    }
    
    public void setVerify(VerificationCodeProperties verify) {
        this.verify = verify;
    }
    
    public SmsCodeProperties getSms() {
        return sms;
    }
    
    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
    
    public String getLoginMobile() {
        return loginMobile;
    }
    
    public void setLoginMobile(String loginMobile) {
        this.loginMobile = loginMobile;
    }
}
