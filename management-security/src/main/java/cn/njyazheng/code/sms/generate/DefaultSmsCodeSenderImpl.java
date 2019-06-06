package cn.njyazheng.code.sms.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSmsCodeSenderImpl implements SmsCodeSender {
    
    private static final Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSenderImpl.class);
    
    @Override
    public void send(String mobile, String code) {
        logger.info("Send code to Communications Company,you need implements interface:SmsCodeSender");
        logger.debug("Send code:{} to phone:{}", code, mobile);
    }
}
