package exceptions;

import dto.UserDTO;

public class InvalidCPRException extends DALException
{
	private static final long serialVersionUID = 7355418346336739232L;
	private UserDTO current;

	public InvalidCPRException(String msg) {
		super(msg);
	}

	public InvalidCPRException(String msg, UserDTO current){
		super(msg);
		this.current=current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}
