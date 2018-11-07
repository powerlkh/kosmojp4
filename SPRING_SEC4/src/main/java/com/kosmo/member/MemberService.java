package com.kosmo.member;

import java.util.ArrayList;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


public interface MemberService {

//	<global-method-security jsr250-annotations="enabled" />
//	@Secured("ROLE_ADMIN, ROLE_USER")
	
//	@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
	public MemberVO memberSearchByID(String mid);

//	@Secured("ROLE_ADMIN")
	public int memberDelete(int mid);

//	@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
	public int memberInsert(MemberVO memberVO);

	


//	<global-method-security pre-post-annotations="enabled" />
//	<global-method-security>
//		<protect-pointcut expression="execution(* com.mycompany.*Service.*(..))" access="ROLE_USER"/>
//	</global-method-security>
	   
//	@PreAuthorize("isAnonymous()") 
//	@PreAuthorize("isFullyAuthenticated()")
	public ArrayList<MemberVO> memberList();

//	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
//	<sec:authorize access="isFullyAuthenticated()">
//    	Fully Authenticated
//   </sec:authorize>
// 
	
}
