package pakageMember;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberService;
import Eception.EmptyMemberInfoException;
import Eception.NotFoundMemberInfoException;
import Eception.OverflowMemberInfoException;
import dto.MemberUpdate;

/**
 * Servlet implementation class update
 */
@WebServlet("/member/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MemberUpdate memberUpdateInfo =new MemberUpdate(request);
			
			MemberService ms =new MemberService();
			boolean isUpdate =ms.update(memberUpdateInfo);
			if(isUpdate) {
				response.setStatus(200);
			}else {
				response.setStatus(400);
			}
			
		} catch (EmptyMemberInfoException | OverflowMemberInfoException e) {
			response.setStatus(400);
		} catch(SQLException e) {
			response.setStatus(500);
		}catch(NotFoundMemberInfoException e) {
			response.setStatus(404);
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
