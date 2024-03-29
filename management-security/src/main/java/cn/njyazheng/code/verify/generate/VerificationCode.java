package cn.njyazheng.code.verify.generate;

import cn.njyazheng.code.CheckCode;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class VerificationCode extends CheckCode {
    private BufferedImage bufferedImage;
   
    public VerificationCode(){}
    
    /**
     *
     * @param bufferedImage
     * @param code
     * @param time 过期时间段,秒
     */
    public VerificationCode(BufferedImage bufferedImage, String code,  LocalDateTime time) {
        super(code,time);
        this.bufferedImage = bufferedImage;
    }
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    
    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
    
}
