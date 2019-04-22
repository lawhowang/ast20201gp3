package ast20201.project.repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ast20201.project.model.SiteConfig;

@Repository
public class SettingRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SiteConfig getSiteConfig() {
        SiteConfig configs = jdbcTemplate.query("SELECT * FROM config", (ResultSet rs) -> {
            Map<String, String> map = new HashMap<>();
            while (rs.next()) {
                String key = rs.getString("key");
                String val = rs.getString("val");
                map.put(key, val);
            }
            SiteConfig config = new SiteConfig(map);
            return config;
        });
        return configs;
    }

    public void updateSiteConfig(SiteConfig config) {
        System.out.println("en");
        for (Map.Entry<String, String> entry : config.getConfig().entrySet()) {
            System.out.println(entry.getValue());
            jdbcTemplate.update("UPDATE config SET val = ? WHERE `key` = ?",
                    new Object[] { entry.getValue(), entry.getKey() });
        }
    }
}
