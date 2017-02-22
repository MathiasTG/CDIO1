package exceptions;

import dto.UserDTO;

public class EmptyStoreException extends DALException {
	private static final long serialVersionUID = 7355418346336739232L;
	private UserDTO current;

	public EmptyStoreException(String msg) {
		super(msg);
	}

	public EmptyStoreException(String msg, UserDTO current) {
		super(msg);
		this.current = current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}
