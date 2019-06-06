package cn.njyazheng.code.sms.generate;

import cn.njyazheng.code.CheckCode;

import java.time.LocalDateTime;

public class SmsCode extends CheckCode {
    public SmsCode() {
    }
    
    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }
}
