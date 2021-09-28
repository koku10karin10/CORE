package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RecordModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "recordValue")
	private  Double recordValue;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "recordedDay")
	private Date recordedDay;
	
	
	
	@ManyToOne
	private SubQuestModel sqm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRecordValue() {
		return recordValue;
	}

	public void setRecordValue(Double recordValue) {
		this.recordValue = recordValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getRecordedDay() {
		return recordedDay;
	}

	public void setRecordedDay(Date recordedDay) {
		this.recordedDay = recordedDay;
	}

	public SubQuestModel getSqm() {
		return sqm;
	}

	public void setSqm(SubQuestModel sqm) {
		this.sqm = sqm;
	}

}
