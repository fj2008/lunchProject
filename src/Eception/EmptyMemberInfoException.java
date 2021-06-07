package Eception;
//회원 정보가 전달되지 않았을 때 발생시킬 예외
public class EmptyMemberInfoException extends Exception{

	/**
	 * 나만의 예외처리를할때 추가시키는 코드
	 */
	private static final long serialVersionUID = -2027077871412814962L;

	public EmptyMemberInfoException(String msg) {
		super(msg);
	}

}
