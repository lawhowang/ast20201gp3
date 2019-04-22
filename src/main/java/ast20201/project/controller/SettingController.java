package ast20201.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ast20201.project.model.SiteConfig;
import ast20201.project.service.SettingService;

@RequestMapping(value = "/api/setting")
@Controller
public class SettingController {

	@Autowired
	SettingService settingService;

	@RequestMapping(value = "/site-title", method = RequestMethod.GET)
	public ResponseEntity<?> getSiteTitle() {
		String siteTitle = settingService.getSiteConfig().getConfig().get("Site Title");
		return ResponseEntity.ok("{ \"siteTitle\": \"" + siteTitle + "\" }");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getSiteConfig() {
		SiteConfig configs = settingService.getSiteConfig();
		return ResponseEntity.ok(configs);
	}
}
