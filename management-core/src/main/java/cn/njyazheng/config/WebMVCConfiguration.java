package cn.njyazheng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebMVCConfiguration.class);
    @Autowired
    private PageMappingProperties pageMappingProperties;
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        Set<String> pathMapping = pageMappingProperties.getPathMappingPage();
        //不符合格式要求的过滤掉
        pathMapping.stream().filter(p -> p.indexOf("->") > 0 && p.indexOf(">") < p.length() - 1 && (p.indexOf("page:") > 0 || p.indexOf("status:") > 0)).forEach(p -> {
            String[] params = p.split("->");
            if (params[1].indexOf("page:") == 0) {
                registry.addViewController(params[0]).setViewName(params[1].replace("page:", "").trim());
            } else {
                registry.addViewController(params[0]).setStatusCode(HttpStatus.valueOf(Integer.parseInt(params[1].replace("status:", "").trim())));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("path:{} -> {} mapping success", params[0], params[1]);
            }
        });
        
    }
}
