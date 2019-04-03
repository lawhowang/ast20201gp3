package ast20201.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ast20201.project.model.SiteConfig;

@Repository
public class SettingRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SiteConfig> getSiteConfig() {
        List<SiteConfig> configs = jdbcTemplate.query("SELECT * FROM config",
                new BeanPropertyRowMapper<SiteConfig>(SiteConfig.class));
        return configs;
    }

    public void updateSiteConfig(List<SiteConfig> configs) {
        try {
            for (SiteConfig config : configs) {
                System.out.println(config.getId());
                jdbcTemplate.update("UPDATE config SET val = ? WHERE id = ?",
                        new Object[] { config.getVal(), config.getId() });
            }
        } catch (Exception ex) {

        }
    }
}
