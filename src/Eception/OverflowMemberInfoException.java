package Eception;
//회원정보의 길이가 지정한 길이를 초과했을 경우 
public class OverflowMemberInfoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6002905233434872477L;

	public OverflowMemberInfoException(String msg) {
		super(msg);
	}
	
}
