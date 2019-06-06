package cn.njyazheng.code.sms.generate;


import cn.njyazheng.param.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class GenerateSmsCodeImpl implements GenerateSmsCode {
    @Autowired
    SecurityProperties configProperties;
    
    @Override
    public SmsCode generate(HttpServletRequest request) {
        String code=RandomStringUtils.randomNumeric(6);
        return new SmsCode(code, LocalDateTime.now().plusMinutes(configProperties.getSms().getExpire()));
    }
}
