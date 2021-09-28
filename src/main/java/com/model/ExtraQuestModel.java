package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Valueに関係のないような普段めんどくさいと思うようなことをクエストにする。
@Entity
public class ExtraQuestModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "extraQuestName")
	private String extraQuestName;
	
	@Column(name = "clearCount")
	private Integer clearCount;
	
	@Column(name = "experiencePoint")
	private Integer experiencePoint;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExtraQuestName() {
		return extraQuestName;
	}

	public void setExtraQuestName(String extraQuestName) {
		this.extraQuestName = extraQuestName;
	}

	public Integer getClearCount() {
		return clearCount;
	}

	public void setClearCount(Integer clearCount) {
		this.clearCount = clearCount;
	}

	public Integer getExperiencePoint() {
		return experiencePoint;
	}

	public void setExperiencePoint(Integer experiencePoint) {
		this.experiencePoint = experiencePoint;
	}

}
