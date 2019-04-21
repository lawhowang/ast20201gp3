package ast20201.project.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.repository.UserRepository;

@Service
public class CreditService {

	@Autowired
	UserRepository userRepository;

	public BigDecimal getCredits(long userId) {
        return userRepository.getUser(userId).getCredits();
    }
}
