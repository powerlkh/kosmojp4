package com.kosmo.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.kosmo.member.MemberService;
import com.kosmo.member.MemberVO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	MemberService memberService;

	//static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	static final ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername 실행....");
		if(memberService == null) System.out.println("memberService null....");
		
		//-------------------------------------------
		//DB에서 회원 정보  데이터를 읽어 옴.
		MemberVO memberVO = memberService.memberSearchByID(username);
		if (memberVO == null) {
			System.out.println("3회이상.....");
			throw new UsernameNotFoundException("Not found with username");
		}

//		Map<String, Object> user = sqlSession.selectOne("user.selectUser",username);
//		if(user == null ) throw new UsernameNotFoundException(username);

		//-------------------------------------------
		//Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		//authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		//if(memberVO.getMpw() != customUserDetails.getPassword()
		if (memberVO.getMgubun() != null && memberVO.getMgubun().trim().length() > 0) {
            if (memberVO.getMgubun().equals("a")) {
            	authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
            	authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
		
		
		//vo.getMpw() 
		//String encodedPassword = passwordEncoder.encode(memberVO.getMpw());
		//String mpwSha = shaPasswordEncoder.encodePassword(memberVO.getMpw(), "KOSMO-SEED");

		CustomUserDetails customUserDetails = new CustomUserDetails(
				username, memberVO.getMpw(),
				true, true, true, true
				, authList, memberVO);

		return customUserDetails;
	}

}
