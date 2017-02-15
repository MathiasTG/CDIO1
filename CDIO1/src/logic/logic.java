package logic;

import java.util.List;

import dal.IUserDAO;
import dto.UserDTO;

public class logic implements ILogic {

	private IUserDAO userDAO;

	public logic(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDTO getUser(int userId) throws DALException {

		return userDAO.getUser(userId);

	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// TODO Auto-generated method stub
		return userDAO.getUserList();
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		return userDAO.createUser(user);
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		userDAO.updateUser(user);

	}

	@Override
	public void deleteUser(int userId) throws DALException {
		// TODO Auto-generated method stub

		userDAO.deleteUser(userId);

	}

}
