package ast20201.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ast20201.project.model.User;
import ast20201.project.model.UserWithProfile;
import ast20201.project.service.ProfileService;
import ast20201.project.service.UserService;
import ast20201.project.validation.ValidationGroup;

@RequestMapping(value = "/api/profile")
@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProfile() {
        User user = userService.getCurrentUser();
        UserWithProfile userWithProfile = profileService.getProflie(user.getId());
        return ResponseEntity.ok(userWithProfile);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfile(@Validated({ ValidationGroup.EditUser.class }) @RequestBody UserWithProfile userWithProfile) {
        User user = userService.getCurrentUser();
        UserWithProfile dbuserWithProfile = profileService.updateProfile(user.getId(), userWithProfile);
        return ResponseEntity.ok(dbuserWithProfile);
    }
}