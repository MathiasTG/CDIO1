package exceptions;

import dto.UserDTO;

public class noRoleException extends DALException
{
	private static final long serialVersionUID = 7355418346396739232L;
	private UserDTO current;

	public noRoleException(String msg) {
		super(msg);
	}

	public noRoleException(String msg, UserDTO current){
		super(msg);
		this.current=current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}