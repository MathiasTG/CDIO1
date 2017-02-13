package presentation;

import data.IUserDAO;

public class TUI {

	private ILogic logic;
	
	public TUI(IUserDAO UserDAO){
		this.UserDAO = UserDAO;
	}
	
}
