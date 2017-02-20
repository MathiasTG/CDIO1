package exceptions;

import dto.UserDTO;

public class emptyStoreException extends Exception {
	private static final long serialVersionUID = 7355418346336739232L;
	private UserDTO current;

	public emptyStoreException(String msg) {
		super(msg);
	}

	public emptyStoreException(String msg, UserDTO current) {
		super(msg);
		this.current = current;
	}

	public UserDTO getCurrentUser() {
		return current;
	}
}
