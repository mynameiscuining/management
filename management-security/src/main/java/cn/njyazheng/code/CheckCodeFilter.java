package cn.njyazheng.code;

import cn.njyazheng.code.verify.generate.VerificationCode;
import cn.njyazheng.param.SecurityProperties;
import cn.njyazheng.param.SecuritySessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

//OncePerRequestFilter保证过滤器只被调用一次
//InitializingBean等相应的参数初始化完成之后再来初始化本类
public abstract class CheckCodeFilter extends OncePerRequestFilter implements InitializingBean {
    private AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler;
    private SecurityProperties configProperties;
    private Set<String> codeUrls;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    /**
     * InitializingBean 初始化其他属性完成后在此进行初始本类
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        codeUrls = configProperties.getVerify().getCodeUrls();
        codeUrls.add(configProperties.getLoginForm());
        
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (codeUrls.stream().filter(codeUrls -> antPathMatcher.match(codeUrls, httpServletRequest.getRequestURI())).count() > 0L) {
            try {
                validateCode(httpServletRequest);
            } catch (CheckCodeFailureException e) {
                simpleUrlAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                logger.debug("code authenticated failure:"+e.getLocalizedMessage());
                return;
            }
            
        }
        //如果不符合登录的url,也要调用过滤器链中后面的过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    
    protected abstract void validateCode(HttpServletRequest httpServletRequest) throws CheckCodeFailureException;
    
    public AuthenticationFailureHandler getSimpleUrlAuthenticationFailureHandler() {
        return simpleUrlAuthenticationFailureHandler;
    }
    
    public void setSimpleUrlAuthenticationFailureHandler(AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler) {
        this.simpleUrlAuthenticationFailureHandler = simpleUrlAuthenticationFailureHandler;
    }
    
    public SecurityProperties getConfigProperties() {
        return configProperties;
    }
    
    public void setConfigProperties(SecurityProperties configProperties) {
        this.configProperties = configProperties;
    }
    
    public Set<String> getCodeUrls() {
        return codeUrls;
    }
    
    public void setCodeUrls(Set<String> codeUrls) {
        this.codeUrls = codeUrls;
    }
}
