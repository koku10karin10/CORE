package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.BigQuestModel;
import com.model.ExtraQuestModel;
import com.model.GItemModel;
import com.model.GachaModel;
import com.model.RarityModel;
import com.model.RecordModel;
import com.model.SubQuestModel;
import com.model.ValueModel;
import com.service.BigQuestRepository;
import com.service.ExtraQuestRepository;
import com.service.GItemRepository;
import com.service.GachaRepository;
import com.service.RarityRepository;
import com.service.RecordRepository;
import com.service.SubQuestRepository;
import com.service.ValueRepository;
import com.service.gacha.GachaGacha;
import com.service.level.LevelService;

@Controller
public class MainController {
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
private BigQuestRepository BQR;
	@Autowired
	private SubQuestRepository SQR;
	@Autowired
	private ValueRepository vR;
	@Autowired
	private RecordRepository rR;
	@Autowired
	private ExtraQuestRepository EQR;
	@Autowired
	private GachaRepository gR;
	@Autowired
	private GItemRepository gIR;
	@Autowired 
	private RarityRepository rarityR;
	@Autowired
	private GachaGacha gacha;
	@Autowired
	private LevelService ls;
	
	Integer BigQuestGachaPointAtCleared = 100;
	Integer SubQuestGachaPointAtCleared = 30;
	Integer RecordGachaPointAtRecorded = 1;
	Integer BigQuestExperiencePointAtCleared = 30;
	
	
	@GetMapping("/")
	public String index(Model model) {
		List<ValueModel> values = (List<ValueModel>) vR.findAll();
		model.addAttribute("values",values);
		return "index";
	}
	
	@GetMapping("/value/edit")
	public String valueEdit(Model model) {
		List<ValueModel> values = (List<ValueModel>) vR.findAll();
		model.addAttribute("values",values);
		return "value/editvalue";
	}
	
	@GetMapping("/value/edit/{id}")
	public String valueDetailEdit(Model model,@PathVariable ValueModel id) {
		model.addAttribute("value",id);
		return "value/valueDetailEdit";
	}
	
	@PostMapping("/value/edit/{id}")
	public String editValueDetail(@PathVariable ValueModel id,@RequestParam String value,@RequestParam Integer ranking){
		ValueModel vm = vR.getById(id.getId());
		vm.setValue(value);
		vm.setRanking(ranking);
		vR.save(vm);
		return "redirect:/value/edit";
	}
	
	@GetMapping("/value/new")
	public String valueNew(ValueModel vm,Model model){
		List<ValueModel> values = (List<ValueModel>) vR.findAll();
		model.addAttribute("values",values);
		return "value/newvalue";
	}
	
	@PostMapping("/value/new")
	public String newValue(ValueModel vm,Model model){
			vm.setLevel(1);
			vm.setValueExperience(0);
			vR.save(vm);
			return "redirect:/value/edit";
	}
	
	@PostMapping("/value/delete/{id}")
	public String deleteValue(@PathVariable ValueModel id,Model model) {
		System.out.println(id.getValue());
		vR.delete(id);
		return "redirect:/value/edit"; 
	}
	
	
	
	//BigQuest
	@GetMapping("/bq")
	public String bqIndex(Model model) {
		List<BigQuestModel> bqms = (List<BigQuestModel>) BQR.findAll();
		model.addAttribute("bqms",bqms);
		return "quest/questIndex";
	}
	
	@GetMapping("/bq/new")
	public String questNew(BigQuestModel bqm,Model model){
		List<ValueModel> vms = (List<ValueModel>) vR.findAll();
		model.addAttribute("vms",vms);
		return "quest/newBQuest";
	}
	
	@PostMapping("/bq/new")
	public String newQuest(BigQuestModel bqm,ValueModel vm,Model model){
		bqm.setValueModel(vm);
		bqm.setClear(false);
		BQR.save(bqm);
		return "redirect:/bq";
	}
	
	@GetMapping("/bq/edit/{bid}")
	public String bQuestEdit(@PathVariable BigQuestModel bid,Model model) {
		List<ValueModel> vms = (List<ValueModel>) vR.findAll();
		model.addAttribute("vms",vms);
		model.addAttribute("bqm",bid);
		return "quest/questEdit";
	}
	
	@PostMapping("/bq/edit/{bid}")
	public String editBQuest(@PathVariable BigQuestModel bid , @RequestParam String questName,@RequestParam ValueModel valueModel) {
		BigQuestModel bqm = BQR.getById(bid.getId());
		bqm.setQuestName(questName);
		bqm.setValueModel(valueModel);
		BQR.save(bqm);
		return "redirect:/bq";
	}
	
	@PostMapping("/bq/delete/{id}")
	public String deleteBQuest(@PathVariable BigQuestModel id,Model model) {
		id.setValueModel(null);
		BQR.delete(id);
		return "redirect:/bq"; 
	}
	
	@GetMapping("/bq/{id}")
	public String bqIndexByValueId(@PathVariable ValueModel id,Model model) {
		List<BigQuestModel> bqms = BQR.findBQByVMId(id);
		model.addAttribute("bqms",bqms);
		return "quest/questIndex";
	}
	
	@PostMapping("/bq/clear/{bid}")
	public String clearBQuest(@PathVariable BigQuestModel bid) {
		BigQuestModel bqm = BQR.getById(bid.getId());
		bqm.setClear(true);
		ls.valueLevelUp(bid.getValueModel().getId(), BigQuestExperiencePointAtCleared);
		//記録した日付を生成
				long miliseconds = System.currentTimeMillis();
		        Date currentDay = new Date(miliseconds);
		bqm.setFinishedDay(currentDay);
		BQR.save(bqm);
		gacha.gachaPointUp(BigQuestGachaPointAtCleared);
		return "redirect:/bq";
	}
	
	//SubQuest
	@GetMapping("/sq/{bid}")
	public String sqIndex(@PathVariable BigQuestModel bid ,Model model) {
		System.out.println(bid.getQuestName());
		List<SubQuestModel> sqms = SQR.findSubByBQMId(bid);
		model.addAttribute("sqms",sqms);
		model.addAttribute("bid",bid);
		return "subQuest/subQuestIndex";
	}
	
	//recordIndexから戻ってくる時よう
	@GetMapping("/sq/back/{sid}")
	public String sqIndexbysid(@PathVariable SubQuestModel sid,Model model) {
		BigQuestModel bid = sid.getBqm();
		System.out.println(bid.getQuestName());
		List<SubQuestModel> sqms = SQR.findSubByBQMId(bid);
		model.addAttribute("sqms",sqms);
		model.addAttribute("bid",bid);
		return "subQuest/subQuestIndex";
	}
	
	@GetMapping("/sq/new/{bid}")
	public String subQuestNew(SubQuestModel sqm,@PathVariable BigQuestModel bid,Model model){
		model.addAttribute("bqm",bid);
		return "subQuest/newSQuest";
	}
	
	@PostMapping("/sq/new/{bid}")
	public String newSQuest(SubQuestModel sqm,@PathVariable BigQuestModel bid,Model model){
		sqm.setBqm(bid);
		sqm.setClear(false);
		SQR.save(sqm);
		return "redirect:/sq/{bid}";
	}
	
	@PostMapping("/sq/clear/{sid}")
	public String clearSQuest(@PathVariable SubQuestModel sid,@RequestParam Integer bid) {
		SubQuestModel sqm = SQR.getById(sid.getId());
		sqm.setClear(true);
		SQR.save(sqm);
		gacha.gachaPointUp(SubQuestGachaPointAtCleared);
		return "redirect:/sq/"+bid.toString();
	}
	
	@GetMapping("/sq/edit/{sid}")
	public String sQuestEdit(@PathVariable SubQuestModel sid,Model model) {
		List<BigQuestModel> bqms = (List<BigQuestModel>) BQR.findAll();
		model.addAttribute("bqms",bqms);
		model.addAttribute("sqm",sid);
		return "subQuest/subQuestEdit";
	}
	
	@PostMapping("/sq/edit/{sid}")
	public String editSQuest(@PathVariable SubQuestModel sid , @RequestParam String subQuestName,@RequestParam Integer SQExperiencePoint,@RequestParam String unit,@RequestParam BigQuestModel BQModel) {
		SubQuestModel sqm = SQR.getById(sid.getId());
		sqm.setSubQuestName(subQuestName);
		sqm.setSQExperiencePoint(SQExperiencePoint);
		sqm.setUnit(unit);
		sqm.setBqm(BQModel);
		SQR.save(sqm);
		return "redirect:/sq/"+sqm.getBqm().getId();
	}

	@PostMapping("/sq/delete/{sid}")
	public String deleteSQuest(@PathVariable SubQuestModel sid,Model model) {
		BigQuestModel bqm = sid.getBqm();
		Integer bid = bqm.getId();
		SQR.delete(sid);
		return "redirect:/sq/"+bid;
	}
	
	//Record
	@GetMapping("/record/{sid}")
	public String recordIndex(@PathVariable SubQuestModel sid ,Model model) {
		System.out.println(sid.getSubQuestName());
		List<RecordModel> records = rR.findRecordBySQId(sid);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E");
		model.addAttribute("df",df);
		model.addAttribute("records",records);
		model.addAttribute("sqm",sid);
		return "record/recordIndex";
	}
	
	@PostMapping("/record/new/{sid}")
	public String newRecord(RecordModel rm,@PathVariable SubQuestModel sid,Model model){
		rm.setSqm(sid);
		//記録した日付を生成
		long miliseconds = System.currentTimeMillis();
        Date currentDay = new Date(miliseconds);
        rm.setRecordedDay(currentDay);
		rR.save(rm);
		gacha.gachaPointUp(RecordGachaPointAtRecorded);
		return "redirect:/record/"+sid.getId();
	}
	
	
	@GetMapping("/record/edit/{rid}")
	public String recordEdit(@PathVariable RecordModel rid,Model model) {
		model.addAttribute("r",rid);
		return "record/recordEdit";
	}
	
	@PostMapping("/record/edit/{rid}")
	public String editRecord(@PathVariable RecordModel rid , @RequestParam String comment,@RequestParam Double recordValue) {
		RecordModel rm = rR.getById(rid.getId());
		rm.setRecordValue(recordValue);
		rm.setComment(comment);
		rR.save(rm);
		return "redirect:/record/"+rm.getSqm().getId();
	}
	
	//ExtraQuest
	@GetMapping("/eq")
	public String eqIndex(Model model) {
		List<ExtraQuestModel> eqms = (List<ExtraQuestModel>) EQR.findAll();
		model.addAttribute("eqms",eqms);
		return "quest/extraQuestIndex";
	}
	
	@GetMapping("/eq/new")
	public String eQuestNew(ExtraQuestModel eqm,Model model){
		return "quest/newEQuest";
	}
	
	@PostMapping("/eq/new")
	public String newEQuest(ExtraQuestModel eqm,Model model){
			eqm.setClearCount(0);
			EQR.save(eqm);
			return "redirect:/eq";
	}
	@GetMapping("/eq/edit/{eid}")
	public String eQuestEdit(@PathVariable ExtraQuestModel eid,Model model) {
		model.addAttribute("eqm",eid);
		return "quest/extraQuestEdit";
	}
	
	@PostMapping("/eq/edit/{eid}")
	public String editEQuest(@PathVariable ExtraQuestModel eid , @RequestParam String extraQuestName,@RequestParam Integer experiencePoint) {
		ExtraQuestModel eqm = EQR.getById(eid.getId());
		eqm.setExtraQuestName(extraQuestName);
		eqm.setExperiencePoint(experiencePoint);
		EQR.save(eqm);
		return "redirect:/eq";
	}
	@PostMapping("/eq/count/{eid}")
	public String EQClearCountUp(@PathVariable ExtraQuestModel eid) {
		Integer clearCount = eid.getClearCount();
		clearCount = clearCount+1 ;
		eid.setClearCount(clearCount);
		EQR.save(eid);
		Integer upGachaPoint = eid.getExperiencePoint();
		gacha.gachaPointUp(upGachaPoint);
		List<GachaModel> gm = gR.findAll();
		System.out.println(gm.get(0).getGachaPoint());
		return "redirect:/eq";
	}
	
	@PostMapping("/eq/delete/{eid}")
	public String deleteEQuest(@PathVariable ExtraQuestModel eid,Model model) {
		EQR.delete(eid);
		return "redirect:/eq"; 
	}
	
	//Gacha
	@GetMapping("/gacha")
	public String gachaIndex(Model model) {
		List<GachaModel> gms = gR.findAll();
		GachaModel gm = gms.get(0);
		model.addAttribute("gm",gm);
		List<GItemModel> gim = gIR.findAll();
		Integer gimCount = gim.size();
		model.addAttribute("gimCount",gimCount);
		return "gacha/gachaIndex";
	}
	
	@PostMapping("/gacha/start")
	public String gachaStart(Model model) {
		String selectedGItem;
		selectedGItem = gacha.gachagacha();
		model.addAttribute("selectedGItem",selectedGItem);
		return "/gacha/gachaResult";
	}
	
	@GetMapping("/gacha/item")
	public String gachaItems(Model model) {
		List<GItemModel> gims =  gIR.findAll();
		model.addAttribute("gitems",gims);
		return "gacha/gachaItemIndex";
	}
	
	@GetMapping("/gacha/item/new")
	public String gachaItemNew(Model model) {
		List<RarityModel> rms = rarityR.findAll();
		model.addAttribute("rms",rms);
		return "gacha/gachaItemNew";
	}
	
	@PostMapping("/gacha/item/new")
	public String gachaNewItem(GItemModel gim,@RequestParam RarityModel rarity_model_id) {
		gim.setRarity(rarity_model_id);
		gIR.save(gim);
		return "redirect:/gacha/item";
	}
	
	@GetMapping("/gacha/item/edit/{gIid}")
	public String gachaItemEdit(@PathVariable GItemModel gIid,Model model) {
		List<RarityModel> rms = rarityR.findAll();
		model.addAttribute("rms",rms);
		model.addAttribute("gItem",gIid);
		return "gacha/gachaItemEdit";
	}
	
	@PostMapping("/gacha/item/edit/{gIid}")
	public String gachaEditItem(@PathVariable GItemModel gIid , Model model,@RequestParam String gItemName,@RequestParam RarityModel rarity_model_id) {
		GItemModel gim = gIR.getById(gIid.getId());
		gim.setgItem(gItemName);
		gim.setRarity(rarity_model_id);
		gIR.save(gim);
		return "redirect:/gacha/item";
	}
	
	@PostMapping("/gacha/item/delete/{gIid}")
	public String deleteGItem(@PathVariable GItemModel gIid,Model model) {
		gIR.delete(gIid);
		return "redirect:/gacha/item";
	}

}
