package cn.njyazheng.controller;

import cn.njyazheng.code.verify.VerificationCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class VerifyCodeController {
    @Autowired
    private VerificationCodeProcessor verificationCodeProcessor;
    @GetMapping("/verify/code")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        verificationCodeProcessor.create(new ServletWebRequest(request,response));
    }
}
