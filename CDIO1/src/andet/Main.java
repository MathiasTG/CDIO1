package andet;

import data.IUserDAO;
import data.IUserDTO;
import data.UserDAO;
import presentation.TUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IUserDTO d = new UserDTO();
		IUserDAO f = new UserDAO(d);
		TUI g = new TUI(f);
		//g.dialog();

	}

}
