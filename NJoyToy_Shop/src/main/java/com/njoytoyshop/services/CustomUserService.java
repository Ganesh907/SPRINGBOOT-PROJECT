package com.njoytoyshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.njoytoyshop.models.CustomUser;
import com.njoytoyshop.repo.CustomUserRepo;

@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
	CustomUserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repo.findByName(username);
		
	}

	
	public List<CustomUser> saveUser(CustomUser user) {
		repo.save(user);
		return repo.findAll();
	}
		

}
