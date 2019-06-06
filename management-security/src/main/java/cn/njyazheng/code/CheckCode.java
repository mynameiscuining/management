package cn.njyazheng.code;

import java.time.LocalDateTime;

public class CheckCode {
    private String code;
    private LocalDateTime expireTime;
    public CheckCode(){}
    public CheckCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public LocalDateTime getExpireTime() {
        return expireTime;
    }
    
    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
