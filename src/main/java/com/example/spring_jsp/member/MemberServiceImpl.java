package com.example.spring_jsp.member;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public String memberJoin(MemberDTO memberDTO) {
	int affectRowCount = this.memberMapper.memberJoin(memberDTO);
	if (affectRowCount == 1) {
		return memberDTO.getId();
		}
	return null;
	}
	
	@Override
	public MemberDTO memberDetail(String id) throws Exception {
		return memberMapper.memberDetail(id);
	}
	
	@Override
	public MemberDTO memberLogin(String id) throws Exception{
		return memberMapper.memberLogin(id);
	}
	
	@Override
	public boolean memberUpdate(MemberDTO memberDTO) {
		int affectRowCount = this.memberMapper.memberUpdate(memberDTO);
		return affectRowCount == 1;
	}
    
}
