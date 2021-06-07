package dto;

import javax.servlet.http.HttpServletRequest;

import Eception.EmptyMemberInfoException;
import Eception.OverflowMemberInfoException;

public class MemberUpdate {
	private String oldPW;//회원정보를 변경하려는 현제 pw
	private String newPW; //회원정보를 변경하려는 사용자의 새로은 pw
	private String id;//회원정보를 변경하려는 사용자의 id
	
	public String getOldPW() {
		return oldPW;
	}


	public String getNewPW() {
		return newPW;
	}


	public void setOldPW(String oldPW) {
		this.oldPW = oldPW;
	}


	public void setNewPW(String newPW) {
		this.newPW = newPW;
	}


	public MemberUpdate(HttpServletRequest request) throws EmptyMemberInfoException, OverflowMemberInfoException{
		this.id = request.getParameter("id");
		this.oldPW = request.getParameter("oldpw");
		this.newPW = request.getParameter("newPw");

		
		if(oldPW == null || newPW == null || id == null) {
		//newPW oldPW 전달되지 않았다면은
		throw new EmptyMemberInfoException("oldPW 또는 newPW가 전달되지 않았습니다.");
		}else if(oldPW.length() >20 ||newPW.length() >20  ) {
			//oldPW 20자이상 newPW 20자이상전달했다면
			throw new OverflowMemberInfoException("oldPW 또는 newPW 지정한 길이를 초과했습니다.");
		}else if(oldPW.trim().length() ==0 ||newPW.trim().length() ==0 || id.trim().length() == 0) {
			//oldPW 또는 newPW 공백으로 전달됐다면
			throw new EmptyMemberInfoException("oldPW 또는 newPW 전달되지 않았습니다.");
		}
		
		
		
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getId() {
		return id;
	}
	
	
}
