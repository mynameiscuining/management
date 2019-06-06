package cn.njyazheng.code.verify.auth;

import cn.njyazheng.code.CheckCodeFailureException;
import cn.njyazheng.code.CheckCodeFilter;
import cn.njyazheng.code.verify.generate.VerificationCode;
import cn.njyazheng.param.SecuritySessionKey;

import org.springframework.util.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//OncePerRequestFilter保证过滤器只被调用一次
//InitializingBean等相应的参数初始化完成之后再来初始化本类
public class VerificationCodeFilter extends CheckCodeFilter {
    /**
     * InitializingBean 初始化其他属性完成后在此进行初始本类
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        super.setCodeUrls(super.getConfigProperties().getVerify().getCodeUrls());
        super.getCodeUrls().add(super.getConfigProperties().getLoginForm());
        
    }
    protected void validateCode(HttpServletRequest httpServletRequest) throws CheckCodeFailureException {
        String code = httpServletRequest.getParameter("imageCode");
        if (StringUtils.isEmpty(code)) {
            throw new CheckCodeFailureException("请输入验证码");
        }
        HttpSession session = httpServletRequest.getSession();
        VerificationCode verificationCode = (VerificationCode) session.getAttribute(SecuritySessionKey.SESSION_KEY_VERIFY_CODE);
        if (verificationCode.isExpire()) {
            throw new CheckCodeFailureException("验证码已过期");
        }
        if (!code.trim().equalsIgnoreCase(verificationCode.getCode())) {
            throw new CheckCodeFailureException("请输入正确验证码");
        }
        session.removeAttribute(SecuritySessionKey.SESSION_KEY_VERIFY_CODE);
    }
}
