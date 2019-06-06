package cn.njyazheng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
    @Autowired
    private ServerProperties serverProperties;
    @Bean
    public ErrorController errorController(ErrorAttributes errorAttributes) {
        return new ErrorPageController(errorAttributes, this.serverProperties.getError());
    }
}
