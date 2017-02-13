package data;



public class UserDAO implements IUserDAO {

	private IUserDTO UserDTO;

	public UserDAO(IUserDTO userDTO) {
		this.UserDTO = UserDTO;
	}

}
