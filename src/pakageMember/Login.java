package pakageMember;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberService;
import Eception.EmptyMemberInfoException;
import Eception.OverflowMemberInfoException;
import dto.MemberInfo;
import util.DBMng;

/**
 * Servlet implementation class Login
 */
@WebServlet("/member/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * 로그인에 필요한 파라미터를 체크하는 메서드
     * @param memberloginInfo 로그인 정보를 갖고있는값
     * @return id,pw가 의미있는값 ->true / id, pw 의미없는값 ->false
     * 
     * /
     */
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //서블릿에서는 흐름만 관리
		//동작관련된 중요한 코드나 기능은 메서드나 생성자로 들어가잇음
	      //db커넥션 연결
	      try { 
	    	  //request에서 회원 정보를 꺼내서 객체로 만듬
	    	  MemberInfo memberLoginInfo = new MemberInfo(request);
		  
	    	  MemberService ms = new MemberService();
	    	  //회원 정보로 로그인 시도
	    	  boolean isLogin =ms.login(memberLoginInfo);
		
			//SELECT 결과가 있다몀
			//로그인 성공
			if(isLogin) {
				//SELECT 결과가 있다면
				//로그인 성공
				response.setStatus(200);
				response.getWriter().print("login success");
			}else {
				//SELECT 결과가 없다면
				//로그인 실패
				response.setStatus(404);
				response.getWriter().print("login filure");
			}
			
			
			
		}catch(EmptyMemberInfoException | OverflowMemberInfoException e) {
			response.setStatus(400);
		}catch (SQLException e) {
			response.setStatus(500);
			
		}
	      
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
