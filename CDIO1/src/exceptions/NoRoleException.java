package exceptions;

import dto.UserDTO;

public class NoRoleException extends DALException
{
	private static final long serialVersionUID = 7355418346396739232L;
	private UserDTO current;

	public NoRoleException(String msg) {
		super(msg);
	}

	public NoRoleException(String msg, UserDTO current){
		super(msg);
		this.current=current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}