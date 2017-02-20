package exceptions;

import dto.UserDTO;

public class InvalidINIException extends Exception
{
	private static final long serialVersionUID = 7355418346336739232L;
	private UserDTO current;

	public InvalidINIException(String msg) {
		super(msg);
	}

	public InvalidINIException(String msg, UserDTO current){
		super(msg);
		this.current=current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}
