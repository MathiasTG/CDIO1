package logic;

import java.util.List;

import dto.UserDTO;

public interface ILogic {

	UserDTO getUser(int userId) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO user) throws DALException;
	void updateUser(UserDTO user) throws DALException;
	void deleteUser(int userId) throws DALException;
	
	public class DALException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7355418246336739222L;

		public DALException(String msg, Throwable e) {
			super(msg,e);
		}

		public DALException(String msg) {
			super(msg);
		}

	}

}