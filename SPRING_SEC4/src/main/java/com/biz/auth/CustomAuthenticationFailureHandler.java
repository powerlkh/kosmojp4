package com.biz.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/* security-context.xml  form-login 설정     */
	private String failureURL;	// /member/loginPage?error
	public void setMySetterFailureURL(String failureURL) {
		this.failureURL = failureURL;
	}

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response, 
			AuthenticationException e) throws IOException, ServletException 
	{
		System.out.println("AuthFailureHandler 실행...." + failureURL);
		//System.out.println(e.getExtraInformation());
		System.out.println(e.getMessage());
		//System.out.println(e.getAuthentication());
		
		//logWrite
		//sms처리
		//---------------------처리-------------------
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  //HTTP Ststus:401
		response.sendRedirect(failureURL);
	}


}
