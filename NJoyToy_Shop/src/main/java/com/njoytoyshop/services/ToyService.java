package com.njoytoyshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njoytoyshop.models.Toy;
import com.njoytoyshop.repo.ToyRepo;

@Service
public class ToyService{
	@Autowired
	ToyRepo repo;
	
	
	public List<Toy> getToys(){
		return repo.findAll();
	}


	public List<Toy> saveToy(Toy toy) {
		repo.save(toy);
		return repo.findAll();
	}

	public Optional<Toy> getToyById(int id) {
		
		return repo.findById(id);
		
	}

	public void deleteToyById(int id) {
		
		repo.deleteById(id);
		
	}
	

}
