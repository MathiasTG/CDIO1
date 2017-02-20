package dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO;
import exceptions.DALException;
import exceptions.UserNotFoundException;

public class UserStore implements IUserDAO {

	private List<UserDTO> users;
	public UserStore() {


	}
	
	
	@SuppressWarnings("unchecked")
	public void loadInfo(){
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(
					new FileInputStream("UserInfo.ser"));
			users = (ArrayList<UserDTO>) ois.readObject();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveInfo(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream( 
					new FileOutputStream(new File("UserInfo.ser")));
			oos.writeObject(users);
			// close the writing.
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public UserDTO getUser(int userId) throws DALException {
		loadInfo();
		for(int i=0;i<users.size();i++){
			if(users.get(i).getUserID()==userId){
				return users.get(i);
			}
		}
		throw new UserNotFoundException("No user has been found with id: "+userId);
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		
		return null;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		// users = readUsers();
		//
		// int tempID = user.getUserID();
		//
		// for(int i = 0; i<users.length; i++){
		// if(tempID== users[i])
		// throw new InvalidIDException("Du har valgt et forkert ID");
		// }
		//
		//
		// if(checkCpr(user.getCpr())){
		// System.out.println("");
		//
		// }else {
		// throw new InvalidCPRException("Forkert CPR");
		// }
		//
		//
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {

	}

	@Override
	public void deleteUser(int userId) throws DALException {

	}

	public boolean checkCpr(String cpr) {
		Date date = null;
		// First try and catch for "-" error
		try {
			String[] parts = cpr.split("-");
			String dateNumber = parts[0]; //
			String number = parts[1]; //

			if (dateNumber.length() == 6 && number.length() == 4) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
					date = sdf.parse(dateNumber);
					if (!dateNumber.equals(sdf.format(date))) {
						return false;
					} else {
						return true;
					}
				} catch (ParseException ex) {
					return false;
				}

			} else
				return false;

		}catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
}
