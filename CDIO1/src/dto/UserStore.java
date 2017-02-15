package dto;
import java.util.List;
import dal.IUserDAO;
import exceptions.DALException;

public class UserStore implements IUserDAO {
		
	private List<UserDTO> users;
	
	public UserStore() {
		

	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
	
		return null;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
	
		return null;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
	
		
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		
		
	}

	@Override
	public void deleteUser(int userId) throws DALException {
	
		
	}
	
	
	
	
	

}
