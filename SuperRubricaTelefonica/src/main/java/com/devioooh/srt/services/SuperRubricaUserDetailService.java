package com.devioooh.srt.services;


import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devioooh.srt.domain.User;
import com.devioooh.srt.repositories.UserRepository;

@Service
public class SuperRubricaUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Utente non trovato");
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), 
				Collections.singleton(new SimpleGrantedAuthority("user")));
	}
	
	

}
