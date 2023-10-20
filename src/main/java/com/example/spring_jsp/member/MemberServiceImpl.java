package com.example.spring_jsp.member;

import java.util.List;

import com.example.spring_jsp.notification.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
    final static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	private final NotificationService notificationService;
    
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

	//로그인시 세션에 메시지 보낼 큐 생성, 이후는 아무 콘트롤러나 서비스에서도 sid를 넣고 메시지를 보내는 것으로 사용가능해짐
	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception{
		notificationService.initSession(memberDTO.getId());
		notificationService.send(memberDTO.getId(), "안녕하세요 " + memberDTO.getId() + "님 환영합니다");
		return memberMapper.memberLogin(memberDTO);
	}

	// 로그아웃시 보유하고있는 웹소켓 세션 삭제, 세션에 보내야할 메시지 큐 삭제 / 메시지는 데이터베이스에 보관하지않음
	public void memberLogout(HttpSession session) {
		String sid = session.getAttribute("sid").toString();
		notificationService.destroySession(sid);
		session.invalidate();
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
	
	@Override
	public MemberDTO whereIsMyId(MemberDTO memberDTO) throws Exception {
		return memberMapper.whereIsMyId(memberDTO);
	}
	
	@Override
	public MemberDTO whereIsMyPw(MemberDTO memberDTO) throws Exception {
		return memberMapper.whereIsMyPw(memberDTO);
	}
}
