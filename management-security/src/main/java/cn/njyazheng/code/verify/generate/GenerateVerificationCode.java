package cn.njyazheng.code.verify.generate;



import javax.servlet.http.HttpServletRequest;

public interface GenerateVerificationCode {
     VerificationCode generate(HttpServletRequest request);
}
