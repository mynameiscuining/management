package cn.njyazheng.code.sms.generate;

public interface SmsCodeSender {
    void send(String mobile, String code);
}
