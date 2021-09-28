package com.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.BigQuestModel;
import com.model.ValueModel;

public interface BigQuestRepository extends JpaRepository<BigQuestModel,Integer>{
	@Query(
			value = "select b from BigQuestModel b where b.valueModel = :value_model_id")
	public List<BigQuestModel> findBQByVMId(@Param("value_model_id") ValueModel vm);

}
