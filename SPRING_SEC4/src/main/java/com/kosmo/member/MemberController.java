package com.kosmo.member;


import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosmo.member.MemberServiceImpl;
import com.kosmo.member.MemberVO;

@Controller
@RequestMapping(value = "/memberctl")
public class MemberController {

	@Autowired
	private MemberService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(ModelMap map) {
		return "redirect:/member/loginPage";
	}
	
	//TILES 관련 페이지 랜더링 ====================================================
	@RequestMapping(value = "/tiles", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "tilesbody_member_login_page";
	}
	@RequestMapping(value = "/loginPageByTiles", method = RequestMethod.GET)
	public String loginPageByTiles(Locale locale, Model model) {
		return "tilesbody_member_login_page";
	}
	@RequestMapping(value = "/loginPageByMav", method = RequestMethod.GET)
	public String loginPageByMav(Locale locale, Model model) {
		return "member/login_page";
	}
	//TILES 관련 페이지 랜더링 ====================================================
	
	
	
	//----- @ResponseBody ------	
	@RequestMapping(value = "/responsebody_ajax")
	@ResponseBody
	public ArrayList<MemberVO> resplyList(
			@RequestParam("seq") int seq) { 
		System.out.println("SEQ:"+ seq);
		ArrayList<MemberVO>  list = service.memberList();
		System.out.println(list.size());
		return list;
	}
	
	
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String googleMap() {
		return "member/google";
	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) 
	{
		ModelAndView mav = new ModelAndView();
		if (error != null) {
			mav.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			mav.addObject("msg", "logout successfully.");
		}
		mav.setViewName("member/login_page");
		return mav;
	}

	//org.springframework.security.authentication.BadCredentialsException: Bad credentials
	@RequestMapping(value = "/deniedPage", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "member/denied_page";
	}
	@RequestMapping(value = "/duplicateLoginPage", method = RequestMethod.GET)
	public String duplicateLoginPage(ModelMap model) {
		model.addAttribute("error", "true");
		return "member/duplicate_login_page";
	}
	
	
	
	
//	@RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
//	public String logout(ModelMap model) {
//		//sesson.invalidate()
//		return "member/logout_page";
//	}


//	@RequestMapping(value = "/authok", method = RequestMethod.GET)
//	public String userMain(HttpServleRequst request, Principal p) {
//
//		System.out.println("authok 실행...." + successURL);
//
//		HttpSession session = request.getSession();
//		session.setAttribute("sess_username", p.getName());
//
//		service.memberList();
//		serviece.payList();
//		mav.addObhect\\
//		return mav;
//	}

	@RequestMapping(value = "/userMainPage", method = RequestMethod.GET)
	public String userMain(HttpServletRequest request, ModelMap model
			, Authentication authentication) {

//		시큐리티 컨텍스트 객체
//		SecurityContext context = SecurityContextHolder.getContext();
		// 인증 객체 : getAuthentication
//		Authentication authentication = context.getAuthentication();

		
//		1. SpringContextHolder
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication.getName());
//		authentication.getPrincipal();
//		authentication.getAuthorities();
//		
//		2. principal
//		@RequestMapping("/")
//		public ModelAndMap func(Principal principal) {
//		        System.out.println(principal.getName());
//		        return mav;
//		}
//		
//		3. User
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(user.getUsername());
		 
		System.out.println("getAuthentication:" + authentication.getName());

		HttpSession session = request.getSession();
		session.setAttribute("sess_username", authentication.getName());

		//인증객체 : getPrincipal > customUserDetails
		//Principal principal = (Principal) authentication.getPrincipal();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		System.out.println("getPrincipal:" + customUserDetails.getUsername());


		//인가객체 : getAuthorities
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		while (iter.hasNext()) {
			GrantedAuthority auth = iter.next();
			System.out.println(auth.getAuthority());
		}
		return "member/user_main";
	}

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String memberList(ModelMap map, Principal p) {
		ArrayList<MemberVO>  list = service.memberList();
		System.out.println(list.size());
		map.addAttribute("LVL_LIST", list);
		return "member/list_page";
	}

	@RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
	public String addEmployee( @ModelAttribute(value = "memberVO") MemberVO memberVO, BindingResult result) {
		service.memberInsert(memberVO);
		return "redirect:/member/list";
	}

	@RequestMapping("/admin/delete/{mid}")
	public String deleteEmplyee(@PathVariable("mid") int mid) {
		service.memberDelete(mid);
		return "redirect:/member/list";
	}

}