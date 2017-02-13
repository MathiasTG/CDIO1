package PresentationTier;

import LogicTier.IUserDAO;

public class TUI {

	private IUserDAO UserDAO;
	
	public TUI(IUserDAO UserDAO){
		this.UserDAO = UserDAO;
	}
	
}
