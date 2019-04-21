package ast20201.project.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ast20201.project.model.User;
import ast20201.project.service.CreditService;
import ast20201.project.service.UserService;

@RequestMapping(value = "/api/credits")
@Controller
public class CreditController {

	@Autowired
    CreditService creditService;
    
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getCredit() {
        User user = userService.getCurrentUser();
        BigDecimal credits = creditService.getCredits(user.getId());
		return ResponseEntity.ok(credits);
	}

}