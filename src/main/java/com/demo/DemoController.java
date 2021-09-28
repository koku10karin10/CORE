//package com.demo;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//@Controller
//
//public class DemoController {
//	@PersistenceContext
//	EntityManager entityManager;
//	@Autowired
//	private DemoRepository dR;
//	@Autowired
//	private SubRepository2 sR;
//	@GetMapping("/")
//	public String index(Model model) {
//		List<DemoModel> demos = (List<DemoModel>) dR.findAll();
//		model.addAttribute("demos",demos);
//		return "index";
//		
//	}
//	
//	@PostMapping("/save")
//	public String saveDemo(@ModelAttribute("demoModel") DemoModel demoModel,Model model) {
////		DemoModel dm = new DemoModel();
////		dm.setName(name);
////		dR.save(dm);
////		List<DemoModel> demos = (List<DemoModel>) dR.findAll();
////		model.addAttribute("demos",demos);//
//		dR.save(demoModel);
//		return "redirect:/";
//	}
//	
//	@GetMapping("/{id}")
//	public String subIndex(@PathVariable Integer id,DemoModel demoModel,Model model) {
//		List<DM> dm = sR.findBySubByDMId(demoModel);
//		model.addAttribute("subs", dm);
//		model.addAttribute("id",id);
//		return "subIndex";
//	}
//	
//	@PostMapping("/sub/save/{id}")
//	public String subCreate(DM dm,DemoModel id,Model model) {
//		dm.setDm(id);
//		sR.save(dm);
//		return "redirect:/";
//	}
//	
//	@PostMapping("/delete/{id}")
//	public String delete(@PathVariable DemoModel id,Model model) {
//		System.out.println("ID:jdoajo"+id);
//		dR.delete(id);
////		List<DemoModel> demos = (List<DemoModel>) dR.findAll();
////		model.addAttribute("demos",demos);
//		return "redirect:/";
//	}
//	
//	@PostMapping("/sub/delete/{id}")
//	public String subDelete(@PathVariable DM id,Model model) {
//		System.out.println("ID:jdoajo"+id);
//		sR.delete(id);
////		List<DemoModel> demos = (List<DemoModel>) dR.findAll();
////		model.addAttribute("demos",demos);
//		return "redirect:/{id}";
//	}
//	
//	
//
//}
