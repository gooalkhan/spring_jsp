package com.example.spring_jsp.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
    final static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    
    @Override
    public void printAll() {
        List<MemberDTO> memberDTO = memberMapper.getAll();
        for (MemberDTO m: memberDTO) {
            logger.info(m.getId() + " " + m.getName());
        }
    }
    
	@Override
	public List<MemberDTO> memberSelect() throws Exception {
		return memberMapper.memberSelect();
	}
	
	@Override
	public String memberJoin(MemberJoinDTO memberJoinDTO) {
	int affectRowCount = this.memberMapper.memberJoin(memberJoinDTO);
	if (affectRowCount == 1) {
		return memberJoinDTO.getId();
		}
	return null;
	}
	
	@Override
	public MemberDTO memberDetail(String id) throws Exception {
		return memberMapper.memberDetail(id);
	}
	
	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception{
		return memberMapper.memberLogin(memberDTO);
	}
	
	@Override
	public boolean memberUpdate(MemberDTO memberDTO) {
		int affectRowCount = this.memberMapper.memberUpdate(memberDTO);
		return affectRowCount == 1;
	}
    
	@Override
	public boolean memberDelete(MemberDTO memberDTO) {
		int affectRowCount = this.memberMapper.memberDelete(memberDTO);
		return affectRowCount == 1;
	}
	
	@Override
	public MemberDTO checkIdDuplication(String id) throws Exception {
		return memberMapper.existsById(id);
	}

	@Override
	public MemberDTO checkNameDuplication(String name) throws Exception {
		return memberMapper.existsByName(name);
		
	}

	@Override
	public MemberDTO checkEmailDuplication(String email) throws Exception {
		return memberMapper.existsByEmail(email);
	}
	
	@Override
	public boolean subadminAppoint(MemberDTO memberDTO) {
		int affectRowCount = this.memberMapper.subadminAppoint(memberDTO);
		return affectRowCount == 1;
	}
	
	@Override
	public boolean subadminAppointCancel(MemberDTO memberDTO) {
		int affectRowCount = this.memberMapper.subadminAppointCancel(memberDTO);
		return affectRowCount == 1;
	}
}
