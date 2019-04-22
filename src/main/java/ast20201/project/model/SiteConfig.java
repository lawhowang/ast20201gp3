package ast20201.project.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SiteConfig {
    
    private Map<String, String> config = new HashMap<String, String>();

    public SiteConfig() {

    }
    
    public SiteConfig(Map<String, String> config) {
        this.config = config;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
   
}
