package cn.njyazheng.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "page")
public class PageMappingProperties {
    private Set<String> pathMappingPage=new HashSet<>();
    
    public Set<String> getPathMappingPage() {
        return pathMappingPage;
    }
    
    public void setPathMappingPage(Set<String> pathMappingPage) {
        this.pathMappingPage = pathMappingPage;
    }
}
