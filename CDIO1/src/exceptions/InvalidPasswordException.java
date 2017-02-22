package exceptions;

public class InvalidPasswordException extends DALException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidPasswordException(String msg){
		super(msg);
	}
}
