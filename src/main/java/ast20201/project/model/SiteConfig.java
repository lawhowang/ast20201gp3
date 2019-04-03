package ast20201.project.model;

import org.springframework.stereotype.Component;

@Component
public class SiteConfig {
    private long id;
    private String key;
    private String val;

    public SiteConfig() {

    }
    
    public SiteConfig(long id, String key, String val) {
        this.id = id;
        this.key = key;
        this.val = val;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
