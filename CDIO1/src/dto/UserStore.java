package dto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dal.IUserDAO;
import exceptions.DALException;
import exceptions.DatabaseFullException;
import exceptions.EmptyStoreException;
import exceptions.InvalidCPRException;
import exceptions.InvalidIDException;
import exceptions.InvalidINIException;
import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;

import exceptions.DatabaseFullException;
import exceptions.InvalidUserNameException;

import exceptions.InvalidUserNameException;
import exceptions.NoRoleException;
import exceptions.UserNotFoundException;

public class UserStore implements IUserDAO {

	private List<UserDTO> users;

	private final String ULetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Upper case
	private final String Lletter = "abcdefghijklmnopqrstuvwxyz"; // Lower case
	private final String Number = "0123456789";
	private final String SChars = "!@#$%^&*_=+-/";
	private final int noOfBLetter = 1; // How many Uppercase letters
	private final int noOfNumbers = 1; // How many numbers
	private final int noOfSChars = 1; // How many special chars
	private final int min = 9; // Min lenght
	private final int max = 12; // Max lenght

	private String pathName = "UserInfo.ser";

	public UserStore() {

	}

	@SuppressWarnings("unchecked")
	public void loadInfo() {

		try {
			InputStream file = new FileInputStream(pathName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			// ois = new ObjectInputStream(new FileInputStream("UserInfo.ser"));
			users = (ArrayList<UserDTO>) input.readObject();
			if(users.equals(null))
				users= new ArrayList<UserDTO>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			users = new ArrayList<UserDTO>();
		} catch(StreamCorruptedException e){
			System.out.println("The file is currupted.");
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveInfo() {
		try {
			OutputStream file = new FileOutputStream(pathName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			// ObjectOutputStream oos = new ObjectOutputStream(new
			// FileOutputStream(new File("UserInfo.ser")));
			output.writeObject(users);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
		loadInfo();
		if(users.size()==0)
			throw new EmptyStoreException("The database is empty.");
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserID() == userId) {
				return users.get(i);
			}
		}
		throw new UserNotFoundException("No user has been found with id: " + userId);
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		loadInfo();
		if(users.size()==0)
			throw new EmptyStoreException("There are no users in the database.");
		return users;

	}

	@Override
	public void createUser(UserDTO user) throws DALException {

		loadInfo();

		user.setPassword(pwg());

		if (users.size() == 88) {
			throw new DatabaseFullException("Database is full");
		}
		checkUser(user);
		users.add(user);
		saveInfo();
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		checkUpdatedUser(user);
		loadInfo();
		for (int i = 0; i < users.size(); i++) {
			if (user.getUserID() == users.get(i).getUserID()) {
				users.remove(i);
				users.add(user);
			}
		}
		saveInfo();
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		loadInfo();
		boolean found = false;
		int index = 0;
		for (int i = 0; i < users.size(); i++) {
			if (userId == users.get(i).getUserID()) {
				found = true;
				index = i;
			}
		}
		if (found == true) {
			users.remove(index);
			saveInfo();
		} else
			throw new UserNotFoundException("No user was found with id: " + userId);
	}

	public boolean checkCpr(String cpr) throws InvalidCPRException {
		Date date = null;
		// First try and catch for "-" error
		for(int i =0;i<users.size();i++){
			if(cpr.equals(users.get(i).getCpr())){
				throw new InvalidCPRException("Invalid CPR number. This CPR number is already taken.");
			}
		}
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

	public void checkUser(UserDTO user) throws DALException {

		int tempID = user.getUserID();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserID() == tempID)
				throw new InvalidIDException("UserID already taken.");
		}
		String tempName = user.getUserName();

		if (tempName.length() > 20 && tempName.length() < 2)
			throw new InvalidUserNameException("Wrong name");
		for(int i = 0;i<users.size();i++){
			if(users.get(i).getUserName().equals(user.getUserName()))
					throw new InvalidUserNameException("Username already taken.");
		}

		String tempIni = user.getIni();
		/*
		 * tempIni.matches(".*\\d+.*") ---------------------------- To explain:
		 * .* means any character from 0 to infinite occurence, than the \\d+
		 * (double backslash I think is just to escape the second backslash) and
		 * \d+ means a digit from 1 time to infinite.
		 */
		if (tempIni.matches(".*\\d+.*")) {
			throw new InvalidINIException("Wrong Initial");
		}
		if (tempIni.length() < 2 || tempIni.length() > 4) {
			throw new InvalidINIException("Wrong Initial");
		}

		for (int i = 0; i < users.size(); i++) {
			if (tempIni.equals(users.get(i).getIni()))
				throw new InvalidINIException("Initials already taken");
		}
		String tempCPR = user.getCpr();

		if (checkCpr(tempCPR) == false) {
			throw new InvalidCPRException("Wrong CPR");
		}

		List<String> tempRoles = user.getRoles();

		if (tempRoles.size() == 0)
			throw new NoRoleException("The user has no roles.");
		checkPsw(user.getPassword());

	}

	public void checkUpdatedUser(UserDTO user) throws DALException {

		String tempName = user.getUserName();

		if (tempName.length() > 20 || tempName.length() < 2)
			throw new InvalidUserNameException("Wrong name");

		String tempIni = user.getIni();

		/*
		 * tempIni.matches(".*\\d+.*") ---------------------------- To explain:
		 * .* means any character from 0 to infinite occurence, than the \\d+
		 * (double backslash I think is just to escape the second backslash) and
		 * \d+ means a digit from 1 time to infinite.
		 */
		if (tempIni.matches(".*\\d+.*")) {
			throw new InvalidINIException("Wrong Initial");
		}
		if (tempIni.length() < 2 || tempIni.length() > 4) {
			throw new InvalidINIException("Wrong Initial");
		}

		for (int i = 0; i < users.size(); i++) {
			if (tempIni.equals(users.get(i).getIni()))
				if (users.get(i).getUserID() != user.getUserID()) {
					throw new InvalidINIException("Wrong Initial");
				}
		}
		String tempCPR = user.getCpr();

		if (checkCpr(tempCPR) == false) {
			throw new InvalidCPRException("Wrong CPR");
		}

		List<String> tempRoles = user.getRoles();

		if (tempRoles.size() == 0)
			throw new NoRoleException("The user has to have a role.");
		checkPsw(user.getPassword());

	}

	private void checkPsw(String password) throws DALException {
		if (password.length() > max) {
			throw new InvalidPasswordException("Password too long");
		}
		if (password.length() < min) {
			throw new InvalidPasswordException("Password too short");
		}
		int noOfCAPS = 0;
		int noSCHR = 0;
		int noDigits = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				noOfCAPS++;
			} else if (Character.isDigit(password.charAt(i))) {
				noDigits++;
			} else if (!password.matches("[^A-Za-z0-9 ]")) {
				noSCHR++;
			}
		}
		if (noOfCAPS < noOfBLetter) {
			throw new InvalidPasswordException("Password must contain at least" + noOfBLetter + "upper case character");
		}
		if (noSCHR < noOfSChars) {
			throw new InvalidPasswordException(
					"Password must contain at least" + noOfSChars + "special character [!@#$%^&*_=+-/]");
		}
		if (noDigits < noOfNumbers) {
			throw new InvalidPasswordException("Password must contain at least" + noOfNumbers + "digits");
		}

	}

	private String pwg() {

		Random random = new Random();
		int lenght = random.nextInt(max - min + 1) + min;
		char[] password = new char[lenght];
		int index = 0;
		for (int i = 0; i < noOfBLetter; i++) {
			index = getNI(random, lenght, password);
			password[index] = ULetter.charAt(random.nextInt(ULetter.length()));
		}
		for (int i = 0; i < noOfNumbers; i++) {
			index = getNI(random, lenght, password);
			password[index] = Number.charAt(random.nextInt(Number.length()));
		}
		for (int i = 0; i < noOfSChars; i++) {
			index = getNI(random, lenght, password);
			password[index] = SChars.charAt(random.nextInt(SChars.length()));
		}
		for (int i = 0; i < lenght; i++) {
			if (password[i] == 0) {
				password[i] = Lletter.charAt(random.nextInt(Lletter.length()));
			}
		}
		String returnString = "";
		for (int i = 0; i < password.length; i++) {
			returnString += password[i];
		}
		return returnString;

	}

	private int getNI(Random random, int lenght, char[] password) {
		int index = random.nextInt(lenght);

		while (password[index = random.nextInt(lenght)] != 0)
			;
		return index;
	}
}
