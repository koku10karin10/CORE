//package com.demo;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class DemoModel {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Integer id;
//	@Column(name = "name")
//	private String name;
//	@ManyToOne
//	private DM dm;
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//
//}
