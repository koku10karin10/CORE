package com.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.GItemModel;
import com.model.GachaModel;
import com.model.RarityModel;
@Service
public class GachaGacha {
	@Autowired
	GachaRepository gR;
	@Autowired
	GItemRepository gIR;
	@Autowired
	RarityRepository rarityR;
	Integer gachaPointLimit = 10;
	private String selectedGItem;
	
	public String gachagacha() {
		selectedGItem = "";
		List<GachaModel> gms = gR.findAll();
		GachaModel gm = gms.get(0);
		Integer ticketCount = gm.getGachaTicketCount();
		if(ticketCount>0) {
			selectedGItem =	gachaStart();
			gm.setGachaTicketCount(ticketCount-1);
			gR.save(gm);
			return selectedGItem;
		}else {
			return selectedGItem;
		}
	}
	
	public String gachaStart() {
		List<GItemModel> gims = null;
		Random random = new Random();
		int selectedNumber = random.nextInt(101)+1;
		System.out.println(selectedNumber);
		if(selectedNumber >=1 && selectedNumber <=5){
			//RarityがBADのもの
			gims = getGachaItem(1);
			if(gims.size()>0) {
				//ガチャアイテムの中からランダムに取り出し。
				int num =  (int) (Math.random() * (gims.size()));
				GItemModel gim = gims.get(num);
				return gim.getgItem();
			}else {
				return "Rarity「Bad」のものが当たりました。";
			}
		}else if(selectedNumber >=6 && selectedNumber <=65){
			//RarityがNormalのもの
			gims = getGachaItem(2);
			if(gims.size()>0) {
				//ガチャアイテムの中からランダムに取り出し。
				int num =  (int) (Math.random() * (gims.size()));
				GItemModel gim = gims.get(num);
				return gim.getgItem();
			}else {
				return "Rarity「Normal」のものが当たりました。";
			}
			
		}else if(selectedNumber >=66 && selectedNumber <=95){
			//RarityがRareのもの
			gims = getGachaItem(3);
			if(gims.size()>0) {
				//ガチャアイテムの中からランダムに取り出し。
				int num =  (int) (Math.random() * (gims.size()));
				GItemModel gim = gims.get(num);
				return gim.getgItem();
			}else {
				return "Rarity「Rare」のものが当たりました。";
			}
		}else{
			//RarityがSperRareのもの
			gims = getGachaItem(4);
			if(gims.size()>0) {
				//ガチャアイテムの中からランダムに取り出し。
				int num =  (int) (Math.random() * (gims.size()));
				GItemModel gim = gims.get(num);
				return gim.getgItem();
			}else {
				return "Rarity「Rare」のものが当たりました。";
			}
		}
		
	}
	
	public List<GItemModel> getGachaItem(Integer rarityId){
		List<GItemModel> gims = null;
		RarityModel rm = rarityR.getById(rarityId);
		gims = gIR.findGItemByRarityId(rm);
		return gims;
	}
	
	public void gachaPointUp(Integer upPoint) {
		List<GachaModel> gms = gR.findAll();
		GachaModel gm = gms.get(0);
		Integer gp = gm.getGachaPoint()+upPoint;
		Integer ticketCount = gm.getGachaTicketCount();
		if(gp>=gachaPointLimit) {
			while(gp>=gachaPointLimit) {
				ticketCount+=1;
				gp -=10;
			}
			gm.setGachaPoint(gp);
			gm.setGachaTicketCount(ticketCount);
			gR.save(gm);
		}else {
			gm.setGachaPoint(gp);
			gm.setGachaTicketCount(ticketCount);
			gR.save(gm);
		}
	}
}
