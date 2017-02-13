package Start;

import DataTier.IUserDTO;
import DataTier.UserDTO;
import LogicTier.IUserDAO;
import LogicTier.UserDAO;
import PresentationTier.TUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IUserDTO d = new UserDTO();
		IUserDAO f = new UserDAO(d);
		TUI g = new TUI(f);
		//g.dialog();

	}

}
