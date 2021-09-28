package com.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SubQuestModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "subQuestName")
	private String subQuestName;
	
	@Column(name = "SQExperiencePoint")
	private Integer SQExperiencePoint;
	
	

	@Column(name = "clear")
	private Boolean clear;
	
	@Column(name = "finishdedDay")
	private Date finishededDay;
	
	@Column(name = "unit")
	private String unit;
	
	
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<RecordModel> getRms() {
		return rms;
	}

	public void setRms(List<RecordModel> rms) {
		this.rms = rms;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	private BigQuestModel bqm;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "sqm")
	private List<RecordModel> rms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubQuestName() {
		return subQuestName;
	}

	public void setSubQuestName(String subQuestName) {
		this.subQuestName = subQuestName;
	}
	
	public Integer getSQExperiencePoint() {
		return SQExperiencePoint;
	}

	public void setSQExperiencePoint(Integer sQExperiencePoint) {
		SQExperiencePoint = sQExperiencePoint;
	}

	public Boolean getClear() {
		return clear;
	}

	public void setClear(Boolean clear) {
		this.clear = clear;
	}

	public Date getFinishededDay() {
		return finishededDay;
	}

	public void setFinishededDay(Date finishededDay) {
		this.finishededDay = finishededDay;
	}

	
	public BigQuestModel getBqm() {
		return bqm;
	}

	public void setBqm(BigQuestModel bqm) {
		this.bqm = bqm;
	}

}
