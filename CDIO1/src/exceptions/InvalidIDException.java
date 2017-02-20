package exceptions;

import dto.UserDTO;

public class InvalidIDException extends Exception{
	private static final long serialVersionUID = 7355418246335739232L;
	private UserDTO current;
	public InvalidIDException(String msg) {
		super(msg);
	}
	public InvalidIDException(String msg, UserDTO current){
		super(msg);
		this.current=current;
	}
	public UserDTO getCurrentUser(){
		return current;
	}

}
