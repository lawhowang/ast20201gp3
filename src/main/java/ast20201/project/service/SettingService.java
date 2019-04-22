package ast20201.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.SiteConfig;
import ast20201.project.repository.SettingRepository;

@Service
public class SettingService {
    @Autowired
    SettingRepository settingRepository;

    public SiteConfig getSiteConfig() {
        return settingRepository.getSiteConfig();
    }

	public void updateSiteConfig(SiteConfig config) {
        settingRepository.updateSiteConfig(config);
	}
}
