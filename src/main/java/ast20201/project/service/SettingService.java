package ast20201.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.SiteConfig;
import ast20201.project.repository.SettingRepository;

@Service
public class SettingService {
    @Autowired
    SettingRepository settingRepository;

    public List<SiteConfig> getSiteConfig() {
        return settingRepository.getSiteConfig();
    }

	public void updateSiteConfig(List<SiteConfig> configs) {
        settingRepository.updateSiteConfig(configs);
	}
}
