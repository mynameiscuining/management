package cn.njyazheng.code.sms.generate;




import javax.servlet.http.HttpServletRequest;

public interface GenerateSmsCode {
     SmsCode generate(HttpServletRequest request);
}
