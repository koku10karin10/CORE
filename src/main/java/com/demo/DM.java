//package com.demo;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//
//@Entity
//public class DM {
//	
//	@Id
//	@GeneratedValue
//	@Column(name = "id")
//	private Integer id;
//	@Column(name = "sub")
//	private String sub;
//	
//	
//	@ManyToOne
//	private DemoModel dm;
//	
//	public DemoModel getDm() {
//		return dm;
//	}
//	public void setDm(DemoModel dm) {
//		this.dm = dm;
//	}
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getSub() {
//		return sub;
//	}
//	public void setSub(String sub) {
//		this.sub = sub;
//	}
//
//}
