package com.devioooh.srt.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devioooh.srt.domain.Contact;
import com.devioooh.srt.model.ContactDetail;
import com.devioooh.srt.model.ContactForm;
import com.devioooh.srt.model.ContactListItem;
import com.devioooh.srt.repositories.ContactRepository;

@Service
public class ContactService {
	private ContactRepository contactRepository ;
	
	@Autowired
	public ContactService(ContactRepository contactRepository) {
		this.contactRepository =contactRepository;
	}
	
	public List<ContactListItem> getList(){
		return contactRepository.findAll().stream().map(c -> {
			ContactListItem item = new ContactListItem();
			item.setId(c.getId());
			item.setName(c.getFirstName() +  " " + c.getLastName());
			return item;
		}).collect(Collectors.toList());
	}
	
	public ContactDetail getDetailById(long id) {
		Contact c = contactRepository.findById(id);
		if(c == null) {
			return null;
		}
		return convertToDetail(c);
	}
	
	public ContactDetail save(ContactForm contactForm) {
		Contact c=fromContactForm(contactForm);
		return convertToDetail(contactRepository.create(c));
	}
	
	private Contact fromContactForm(ContactForm contactForm) {
		Contact c =new Contact();
		c.setFirstName(contactForm.getFirstName());
		c.setLastName(contactForm.getLastName());
		c.setEmail(contactForm.getEmail());
		c.setPhone(contactForm.getPhone());
		return c;
	}
	
	private ContactDetail convertToDetail(Contact c) {
		ContactDetail contactDetail = new ContactDetail();
		contactDetail.setId(c.getId());
		contactDetail.setFirstName(c.getFirstName());
		contactDetail.setLastName(c.getLastName());
		contactDetail.setPhone(c.getPhone());
		contactDetail.setPhone(c.getPhone());
		return contactDetail;
	}

}
