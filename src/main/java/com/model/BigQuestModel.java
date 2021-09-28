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
public class BigQuestModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "questName")
	private String questName;
	
	@Column(name = "clear")
	private Boolean clear;
	
	@Column(name = "finishedDay")
	private Date finishedDay;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private ValueModel valueModel;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bqm", orphanRemoval=true)
	private List<SubQuestModel> sqms;
	
	public ValueModel getValueModel() {
		return valueModel;
	}

	public void setValueModel(ValueModel valueModel) {
		this.valueModel = valueModel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	public Boolean getClear() {
		return clear;
	}

	public void setClear(Boolean clear) {
		this.clear = clear;
	}

	public Date getFinishedDay() {
		return finishedDay;
	}

	public void setFinishedDay(Date finishedDay) {
		this.finishedDay = finishedDay;
	}

}
