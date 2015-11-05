package com.keatschinese.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keatschinese.model.RoleModel;
import com.keatschinese.model.UserModel;
import com.keatschinese.repository.UserRepository;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	class SecurityUser extends UserModel implements UserDetails {

		private static final long serialVersionUID = -8983258523367106322L;

		public SecurityUser(UserModel user) {
			if (user != null) {
				this.setId(user.getId());
				this.setName(user.getName());
				this.setCreatedTime(user.getCreatedTime());
				this.setLastUpdatedTime(user.getLastUpdatedTime());
				this.setManageable(user.isManageable());
				this.setPassword(user.getPassword());
				this.setRoles(user.getRoles());
			}
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(RoleModel role : this.getRoles()) {
				authorities.add(new SecurityGrantedAuthority(role));
			}
			return authorities;
		}

		@Override
		public String getUsername() {
			return this.getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
		
	}
	
	class SecurityGrantedAuthority implements GrantedAuthority {
		private static final long serialVersionUID = 861515469092870101L;
		private RoleModel role;
		public SecurityGrantedAuthority(RoleModel role) {
			this.role = role;
		}
		@Override
		public String getAuthority() {
			return this.role.getName();
		}
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("enter loadUserByUsername method!");
		UserModel user = userRepository.findByName(username);
		if (user == null) {
			logger.error("not find user " + username);
			throw new UsernameNotFoundException("Username " + username + " not found!");
		} else {
			return new SecurityUser(user);
		}
	}

}
