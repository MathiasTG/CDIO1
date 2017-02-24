package ui;

import dal.IUserDAO;
import dto.UserStore;
import logic.ILogic;
import logic.logic;

public class Main {
	public static void main(String[] args) {
		IUserDAO d=new UserStore();
		ILogic f=new logic(d);
		TUI t = new TUI(f);
		t.menu();
	}

}
