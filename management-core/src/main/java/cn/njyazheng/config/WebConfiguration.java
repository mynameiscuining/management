package cn.njyazheng.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class WebConfiguration {
    @Autowired
    private ServerProperties serverProperties;
    @Bean
    public ErrorController errorController(ErrorAttributes errorAttributes) {
        return new ErrorPageController(errorAttributes, this.serverProperties.getError());
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        return dataSource;
    }
}
