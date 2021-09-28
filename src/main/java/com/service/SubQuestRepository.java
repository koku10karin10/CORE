package com.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.BigQuestModel;
import com.model.SubQuestModel;

public interface SubQuestRepository extends JpaRepository<SubQuestModel,Integer>{
	@Query(
			value = "select s from SubQuestModel s where s.bqm = :bqm_id")
	public List<SubQuestModel> findSubByBQMId(@Param("bqm_id") BigQuestModel bqm);

}
