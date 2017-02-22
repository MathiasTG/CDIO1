package exceptions;

import dto.UserDTO;

public class databaseFullException extends DALException {
	private static final long serialVersionUID = 7355418346336739232L;
	private UserDTO current;

	public databaseFullException(String msg) {
		super(msg);
	}

	public databaseFullException(String msg, UserDTO current) {
		super(msg);
		this.current = current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}