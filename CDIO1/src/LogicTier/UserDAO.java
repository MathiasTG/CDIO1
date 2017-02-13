package LogicTier;

import DataTier.IUserDTO;

public class UserDAO implements IUserDAO {

	private IUserDTO UserDTO;

	public UserDAO(IUserDTO userDTO) {
		this.UserDTO = UserDTO;
	}

}
