package com.biz.auth;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class CustomAuthenticationManager 
implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthenticationProvider provider = new CustomAuthenticationProvider();

		//authorities = customUserDetails.getAuthorities(); 
		Authentication auth = provider.authenticate(authentication);
		
		Authentication auth2 = null;
		if(!auth.getAuthorities().isEmpty()) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			auth2 = securityContext.getAuthentication();
//			Object securityContextObject = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY); 
//			if(securityContextObject != null){ 
//				securityContext = (SecurityContext)securityContextObject; 
//			}

		}
		return auth2;
	}


}



