package cn.njyazheng.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface CheckCodeProcessor {
    void create(ServletWebRequest servletWebRequest)throws Exception;
}
