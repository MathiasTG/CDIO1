package exceptions;

import dto.UserDTO;

public class DatabaseFullException extends DALException {
	private static final long serialVersionUID = 7355418346336739232L;
	private UserDTO current;

	public DatabaseFullException(String msg) {
		super(msg);
	}

	public DatabaseFullException(String msg, UserDTO current) {
		super(msg);
		this.current = current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}