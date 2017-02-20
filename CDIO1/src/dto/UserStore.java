package dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dal.IUserDAO;
import exceptions.DALException;
import exceptions.InvalidCPRException;
import exceptions.InvalidIDException;

public class UserStore implements IUserDAO {

	private List<UserDTO> users;
	private String theString = "The value of that string";
	private int someInteger = 0;

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

		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {

		String aString = "The value of that string";
		int someInteger = 999;
		String aString2 = " jlndasf ";

		// SS instance = new SS();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("UserInfo.ser")));

		// do the magic
		oos.writeObject(aString + aString2);
		// close the writing.
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("UserInfo.ser"));
		try {
			System.out.println("" + ois.readObject());
			String[] users = new String[2];
			users = (String[]) ois.readObject();
			System.out.println(users.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ois.close();
	}
}
