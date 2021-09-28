package com.service.level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.ValueModel;
import com.service.ValueRepository;

@Service
public class LevelService {
	@Autowired
	private ValueRepository vR;
	public int valueLevelUp(Integer valueModelId,Integer experiencePoint) {
		ValueModel vm;
		Integer valueLevel = vR.getById(valueModelId).getLevel();
		Integer levelExperienceLimit = (valueLevel - 1)*10+10;
		if(experiencePoint > levelExperienceLimit) {
			vm = vR.getById(valueModelId);
			vm.setLevel(vm.getLevel()+1);
			experiencePoint -= levelExperienceLimit;
			vm.setValueExperience(experiencePoint);
			vR.save(vm);
			return experiencePoint;
		}else {
			vm = vR.getById(valueModelId);
			vm.setValueExperience(vm.getValueExperience()+experiencePoint);
			vR.save(vm);
			return experiencePoint;
		}
	}
	public void getExperienceOfValueLevel(Integer valueModelId,Integer getExperiencePoint) {
		
	}

}
