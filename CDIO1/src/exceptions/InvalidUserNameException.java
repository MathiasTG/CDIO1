package exceptions;

import dto.UserDTO;

public class InvalidUserNameException extends DALException
{
	private static final long serialVersionUID = 7355418346396739232L;
	private UserDTO current;

	public InvalidUserNameException(String msg) {
		super(msg);
	}

	public InvalidUserNameException(String msg, UserDTO current){
		super(msg);
		this.current=current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}
