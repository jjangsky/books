package com.bukkeubook.book.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.member.model.dto.MemberRoleDTO;
import com.bukkeubook.book.member.model.dto.RoleDTO;
import com.bukkeubook.book.member.model.dto.UserImpl;
import com.bukkeubook.book.member.model.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberRepository memberRepository;
	
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String empNo) throws UsernameNotFoundException {
		int empNo2 = Integer.valueOf(empNo);
		EmpDTO emp = memberRepository.findByempNo(empNo2);
		if(emp == null) {
			emp = new EmpDTO();
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(emp.getMemberRoleList() != null) {
			List<MemberRoleDTO> roleList = emp.getMemberRoleList();
			
			for(int i = 0; i < roleList.size(); i++) {
				 RoleDTO role = roleList.get(i).getRole();
				 authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
		}
		UserImpl user = new UserImpl(String.valueOf(emp.getEmpNo()), emp.getEmpPwd(), authorities);
		user.setDetails(emp);
		System.out.println("dddddddddddddddddddddddddddddd" + user);
		return user;
	}
	
}
