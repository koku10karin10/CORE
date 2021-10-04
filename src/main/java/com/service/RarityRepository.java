package com.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.RarityModel;

public interface RarityRepository extends JpaRepository<RarityModel,Integer>{
	@Query(
			value = "select r from RarityModel r where r.rarityNumber = :rarityNumber")
	public List<RarityModel> getRarityModelByRarityNumber(@Param("rarityNumber") Integer rarityNumber);


}
