package com.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.ValueModel;

public interface ValueRepository  extends JpaRepository<ValueModel,Integer>{

}
