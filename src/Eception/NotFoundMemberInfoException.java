package Eception;
//회원정보를 찾지 못했을 때 발생시킬 예외
public class NotFoundMemberInfoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7499732062378081304L;

	public NotFoundMemberInfoException(String msg) {
		super(msg);
	}
}
