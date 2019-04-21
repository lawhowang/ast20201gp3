package ast20201.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.UserWithProfile;
import ast20201.project.repository.ProfileRepository;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    public UserWithProfile getProflie(long userId) {
        return profileRepository.getProflie(userId);
    }

	public UserWithProfile updateProfile(long userId, UserWithProfile userWithProfile) {
		profileRepository.updateProfile(userId, userWithProfile);
        return getProflie(userId);
	}
}
