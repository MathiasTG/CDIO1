package exceptions;

import dto.UserDTO;

public class invalidUserNameException extends DALException
{
	private static final long serialVersionUID = 7355418346396739232L;
	private UserDTO current;

	public invalidUserNameException(String msg) {
		super(msg);
	}

	public invalidUserNameException(String msg, UserDTO current){
		super(msg);
		this.current=current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}
