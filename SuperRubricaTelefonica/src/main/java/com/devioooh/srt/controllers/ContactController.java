package com.devioooh.srt.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devioooh.srt.model.ContactDetail;
import com.devioooh.srt.model.ContactForm;
import com.devioooh.srt.services.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

//	@GetMapping("/new")
//	public String contactForm(Model model) {
//		model.addAttribute(new ContactForm());
//		return"contact-form";
//	}
//	@PostMapping("/new")
//	public String submitNewContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult bindingResult, Model model) {
//		if (bindingResult.hasErrors()){
//			return"contact-form";
//		}
//		ContactDetail details=new ContactDetail();
//		details.setFirstName(contactForm.getFirstName());
//		details.setLastName(contactForm.getLastName());
//		details.setPhone(contactForm.getPhone());
//		details.setEmail(contactForm.getEmail());
//		model.addAttribute("contact", details);
//		return "contact-details";
//	}
	
	private ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService=contactService;
		
	}
	
	@GetMapping
	public String contactsList(Model model) {
		model.addAttribute("contacts", contactService.getList());
		return "contact-list";
	}
	
	@GetMapping("/new")
	public String contactForm(Model model) {
		model.addAttribute(new ContactForm());
		return"contact-form";
	}
	
	@PostMapping("/new")
	public String submitNewContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()){
			return"contact-form";
		}
		model.addAttribute("contact", contactService.save(contactForm));
		return "contact-details";
	}
	
	@GetMapping("/{id}")
	public String contactById(@PathVariable ("id") Long id, Model model) {
		ContactDetail contact=contactService.getDetailById(id.longValue());
		if(contact == null)
			return "redirect:/";
		model.addAttribute("contact", contact);
		return "contact-details";
	}
}
