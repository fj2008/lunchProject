package dto;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import Eception.EmptyMemberInfoException;
import Eception.OverflowMemberInfoException;

public class MemberInfo {
	//회원정보를 저장하는 용도의 파일
	private String id;
	private String pw;
	private String joinDate;
	
	public MemberInfo(HttpServletRequest request) throws EmptyMemberInfoException, OverflowMemberInfoException {
		this.id = request.getParameter("id");
		this.pw = request.getParameter("pw");
		
		if(id == null || pw == null) {
			//아이디또는 비밀번호가 전달되지 않았다면은
			throw new EmptyMemberInfoException("아이디 또는 비밀번호가 전달되지 않았습니다.");
		}else if(id.length() >20 || pw.length() >16) {
			//아이디를 20자이상 비밀번호를 20자이상전달했다면
			throw new OverflowMemberInfoException("아이디 또는 비밀번호가 지정한 길이를 초과했습니다.");
		}else if(id.trim().length() ==0 || pw.trim().length() == 0) {
			//아이디 또는 비밀번호가 공백으로 전달됐다면
			throw new EmptyMemberInfoException("아이디 또는 비밀번호가 전달되지 않았습니다.");
		}
			
	}
	public MemberInfo(HttpServletRequest request, LocalDateTime joinDate) throws EmptyMemberInfoException, OverflowMemberInfoException{
		this(request);
		this.joinDate =joinDate.toString();
	}
	
	public MemberInfo(String id, String pw) {
		 this.id =id;
		 this.pw =pw;
		
	}
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getJoinDate() {
		return joinDate;
	}
	

	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
}
	
