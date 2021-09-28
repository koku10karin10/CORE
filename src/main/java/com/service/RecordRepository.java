package com.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.RecordModel;
import com.model.SubQuestModel;

public interface RecordRepository  extends JpaRepository<RecordModel,Integer>{
	@Query(
			value = "select r from RecordModel r where r.sqm = :sqm_id")
	public List<RecordModel> findRecordBySQId(@Param("sqm_id") SubQuestModel sqm);

}
