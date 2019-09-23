package com.edgetech.showoff.service;

import com.edgetech.showoff.model.Role;
import com.edgetech.showoff.model.User;
import com.edgetech.showoff.repository.RoleRepository;
import com.edgetech.showoff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	public boolean isUserAlreadyPresent(User user) {
		// Try to implement this method, as assignment.
		return false;
	}

}
