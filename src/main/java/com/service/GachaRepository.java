package com.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.GachaModel;

public interface GachaRepository extends JpaRepository<GachaModel,Integer>{

}
