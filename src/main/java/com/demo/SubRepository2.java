//package com.demo;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface SubRepository2 extends CrudRepository<DM,Integer>{
//	@Query(
//			value = "select s from DM s where s.dm = :dm_id")
//	public List<DM> findBySubByDMId(@Param("dm_id") DemoModel dm);
//}
