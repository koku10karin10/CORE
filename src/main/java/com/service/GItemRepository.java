package com.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.GItemModel;
import com.model.RarityModel;

public interface GItemRepository extends JpaRepository<GItemModel,Integer>{
	@Query(
			value = "select i from GItemModel i where i.rarity = :rarity")
	public List<GItemModel> findGItemByRarityId(@Param("rarity") RarityModel rarity);

}
