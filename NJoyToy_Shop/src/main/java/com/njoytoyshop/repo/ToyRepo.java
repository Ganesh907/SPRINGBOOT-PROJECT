package com.njoytoyshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.njoytoyshop.models.Toy;

public interface ToyRepo extends JpaRepository<Toy, Integer>{

}
