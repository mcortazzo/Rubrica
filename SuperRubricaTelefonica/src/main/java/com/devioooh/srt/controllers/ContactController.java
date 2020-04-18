package com.devioooh.srt.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devioooh.srt.model.ContactDetail;
import com.devioooh.srt.model.ContactForm;

@Controller
@RequestMapping("/contacts")
public class ContactController {

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
		ContactDetail details=new ContactDetail();
		details.setFirstName(contactForm.getFirstName());
		details.setLastName(contactForm.getLastName());
		details.setPhone(contactForm.getPhone());
		details.setEmail(contactForm.getEmail());
		model.addAttribute("contact", details);
		return "contact-details";
	}
}
