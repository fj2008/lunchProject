package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import Eception.NotFoundMemberInfoException;
import dto.MemberInfo;
import dto.MemberUpdate;
import util.DBMng;

public class MemberService {
	
	public boolean login(MemberInfo memberLoginInfo) throws SQLException{
		boolean isLogin =false;
		
		
			Connection conn =DBMng.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE id = ? AND pw = ?");
		pstmt.setString(1, memberLoginInfo.getId());
		pstmt.setString(2, memberLoginInfo.getPw());
		
		ResultSet rs =pstmt.executeQuery();
		isLogin = rs.next();
		
		DBMng.closeConnection();
		
		
		return isLogin;
	

	}
	
	public boolean join(MemberInfo memberJoinInfo)throws SQLException{
		boolean isJoin =false;
		Connection conn = DBMng.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user(id, pw, joinDate) VALUES(?,?,?)");
		pstmt.setString(1,memberJoinInfo.getId());
		pstmt.setString(2, memberJoinInfo.getPw());
		
		pstmt.setString(3, memberJoinInfo.getJoinDate());
		
		
		int insertResult = pstmt.executeUpdate();
		isJoin =insertResult ==1;
		DBMng.closeConnection();
		return isJoin;
	}
	
	
	public boolean update(MemberUpdate memberUpdate) throws SQLException,NotFoundMemberInfoException{
		boolean isUpdate =false;
		
		
		try {
			
			Connection conn =DBMng.getConnection();
			//회원 정보를 수정하기 전 수정하려는 회원의 정보가 존재하는지 여부를 체크하고
			//회원 정보를 수정할때 사용할 idx값을 가져오는부분
			int updateIdx = selectByIdPW(memberUpdate.getId(), memberUpdate.getOldPW());
			if(updateIdx> -1) {
				//수정하려는 비밀번호가 같다면
				int idx =updateIdx;
			
			
			
			PreparedStatement updatePstmt =conn.prepareStatement("UPDATE user SET pw =? WHERE idx = ?");
			updatePstmt.setString(1, memberUpdate.getNewPW());
			updatePstmt.setInt(2, idx);
			int updateResult =updatePstmt.executeUpdate();
			
			isUpdate =updateResult == 1;//사용자의 정보가 잘 저장이돼서 1을 반환했다면 true반환
			}else {
				//수정하려는 회원 정복가 존재하지 않는다면
				 throw new NotFoundMemberInfoException("회원정보가 없습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBMng.closeConnection();
		
		return isUpdate;
	}
	
	public boolean delete(MemberInfo memberDeleteInfo)throws SQLException, NotFoundMemberInfoException{
		boolean isDelete =false;
			
			
			Connection conn =DBMng.getConnection();
			//회원 정보를 수정하기 전 수정하려는 회원의 정보가 존재하는지 여부를 체크하고
			//회원 정보를 수정할때 사용할 idx값을 가져오는부분
			int deleteIdx =selectByIdPW(memberDeleteInfo.getId(), memberDeleteInfo.getPw());
			if(deleteIdx >-1) {
					//회원 탈퇴를 할 사용자의 정보가 존재한다면
				PreparedStatement Pstmt = conn.prepareStatement("DELETE FROM user WHERE idx = ? ");
				Pstmt.setInt(1,deleteIdx);
				int deleteResult =Pstmt.executeUpdate();
				isDelete =deleteResult == 1;
			}else {
				//수정하려는 회원 정복가 존재하지 않는다면
				 throw new NotFoundMemberInfoException("탈퇴하려는 회원정보가 없습니다.");
			}
			
			DBMng.closeConnection();
			return isDelete;
			
	}
	
	public int selectByIdPW(String id, String pw ) {
		//일치하는 회원정보가 없다고 가지어하고 시작하기 때문에 -1을 저장
		int idx =-1;
		Connection conn;
		try {
			conn = DBMng.getConnection();
			
			PreparedStatement selectPstmt =conn.prepareStatement("SELECT idx FROM user WHERE  id = ? AND  pw = ?");
			selectPstmt.setString(1, id);
			selectPstmt.setString(2, pw);
		
			ResultSet rs =selectPstmt.executeQuery();
			if(rs.next()) {
				//아이디와 비밀번호가 일치하는 회원 정보가 있다면
				idx =rs.getInt("idx");
				
			}
			DBMng.closeConnection();
		} catch (SQLException e) {
			
			
		}
		return idx;
		
	}
}
