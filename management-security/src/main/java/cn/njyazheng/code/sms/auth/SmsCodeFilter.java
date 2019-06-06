package cn.njyazheng.code.sms.auth;

import cn.njyazheng.code.CheckCodeFailureException;
import cn.njyazheng.code.CheckCodeFilter;
import cn.njyazheng.code.sms.generate.SmsCode;
import cn.njyazheng.param.SecuritySessionKey;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//OncePerRequestFilter保证过滤器只被调用一次
//InitializingBean等相应的参数初始化完成之后再来初始化本类
public class SmsCodeFilter extends CheckCodeFilter {
    /**
     * InitializingBean 初始化其他属性完成后在此进行初始本类
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        super.setCodeUrls(super.getConfigProperties().getSms().getCodeUrls());
        super.getCodeUrls().add(super.getConfigProperties().getLoginMobile());
        
    }
    
    protected void validateCode(HttpServletRequest httpServletRequest) throws CheckCodeFailureException {
        String code = httpServletRequest.getParameter("mobileCode");
        if (StringUtils.isEmpty(code)) {
            throw new CheckCodeFailureException("请输入短信动态码");
        }
        HttpSession session = httpServletRequest.getSession();
        SmsCode verificationCode = (SmsCode) session.getAttribute(SecuritySessionKey.SESSION_KEY_SMS_CODE);
        if(verificationCode==null){
            throw new CheckCodeFailureException("请获取短信动态码");
        }
        if (verificationCode.isExpire()) {
            throw new CheckCodeFailureException("短信动态码已过期");
        }
        if (!code.trim().equalsIgnoreCase(verificationCode.getCode())) {
            throw new CheckCodeFailureException("请输入正确短信动态码");
        }
        session.removeAttribute(SecuritySessionKey.SESSION_KEY_SMS_CODE);
    }
}
