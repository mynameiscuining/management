package cn.njyazheng.code;

import org.springframework.security.core.AuthenticationException;

public class CheckCodeFailureException extends AuthenticationException {
    public CheckCodeFailureException(String explanation) {
        super(explanation);
    }
}
