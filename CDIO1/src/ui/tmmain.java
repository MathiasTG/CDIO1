package ui;

import dto.UserStore;
import logic.ILogic;
import logic.logic;

public class tmmain {

	public tmmain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ILogic f=new logic(null);
		TUI t = new TUI(f);
		t.menu();
	}

}
