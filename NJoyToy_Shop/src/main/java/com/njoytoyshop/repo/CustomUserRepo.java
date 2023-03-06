package com.njoytoyshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.njoytoyshop.models.CustomUser;

public interface CustomUserRepo extends JpaRepository<CustomUser, Integer>{

	public CustomUser findByName(String name);
	
}
