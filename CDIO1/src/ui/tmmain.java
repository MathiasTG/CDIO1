package ui;

import dal.IUserDAO;
import dto.UserStore;
import logic.ILogic;
import logic.logic;

public class tmmain {

	public tmmain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		IUserDAO d=new UserStore();
		ILogic f=new logic(d);
		TUI t = new TUI(f);
		t.menu();
	}

}
