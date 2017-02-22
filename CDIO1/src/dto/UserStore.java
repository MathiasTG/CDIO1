package dto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;



import dal.IUserDAO;
import exceptions.DALException;
import exceptions.EmptyStoreException;
import exceptions.InvalidCPRException;
import exceptions.InvalidIDException;
import exceptions.InvalidINIException;
import exceptions.UserNotFoundException;
import exceptions.databaseFullException;
import exceptions.invalidUserNameException;
import exceptions.noRoleException;

public class UserStore implements IUserDAO {

	private List<UserDTO> users;

	private String theString = "The value of that string";
	private int    someInteger = 0;
	
	private static final String ULetter  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  // Upper case
    private static final String Lletter   = "abcdefghijklmnopqrstuvwxyz"; // Lower case
    private static final String Number     = "0123456789";
    private static final String SChars   = "!@#$%^&*_=+-/";
    static int noOfBLetter = 1; // How many Uppercase letters
    static int noOfNumbers = 1; // How many numbers
    static int noOfSChars = 1;   // How many special chars
    static int min = 9;  // Min lenght
    static int max = 12; // Max lenght
    
    String pathName = "UserInfo.ser";
    

	public UserStore() {

	}
	// Test Mode.
	
	public UserStore(String pathName){
		this.pathName= pathName;
	}

	@SuppressWarnings("unchecked")
	public void loadInfo() {
		
		try {
			InputStream file = new FileInputStream(pathName);
		      InputStream buffer = new BufferedInputStream(file);
		      ObjectInput input = new ObjectInputStream (buffer);
			//ois = new ObjectInputStream(new FileInputStream("UserInfo.ser"));
			users = (ArrayList<UserDTO>) input.readObject();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (EOFException e){
			users=new ArrayList();
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
			//ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("UserInfo.ser")));
			output.writeObject(users);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static char[] pwg (int minLenght, int maxLenght, int CAPSNumber, int CHARSNumber,int DIGITSNumber ) {
		
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
        for(int i = 0; i < lenght; i++) {
            if(password[i] == 0) {
                password[i] = Lletter.charAt(random.nextInt(Lletter.length()));
            }
        }
        return password;
		
	}
	
	private static int getNI(Random random, int lenght, char[] password) {
        int index = random.nextInt(lenght);
        while(password[index = random.nextInt(lenght)] != 0);
        return index;
    }
	
	
	@Override
	public UserDTO getUser(int userId) throws DALException {
		loadInfo();
		if(users.size()==0){
			throw new EmptyStoreException("Empty Store");
		}
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
		if(users.size()==0){
			throw new EmptyStoreException("Empty Store");
		}
		return users;
		
	}

	@Override
	public void createUser(UserDTO user) throws DALException {

		loadInfo();
		if(users.size()==88){
			throw new databaseFullException("Database is full");
		}
		checkUser(user);
		users.add(user);
		saveInfo();
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		//checkUser(user);
		boolean updateUserStatus = false;
		loadInfo();
		if(users.size()==0){
			throw new EmptyStoreException("Empty Store");
		}
		for(int i=0;i<users.size();i++){
			if(user.getUserID()==users.get(i).getUserID()){
				users.remove(i);
				users.add(user);
				updateUserStatus = true;
			}
		}
		if(updateUserStatus == false)
			throw new UserNotFoundException("No user has been found with id: " + user.getUserID());
		
		saveInfo();
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		loadInfo();
		if(users.size()==0){
			throw new EmptyStoreException("Empty Store");
		}
		boolean found=false;
		int index=0;
		for(int i=0;i<users.size();i++){
			if(userId==users.get(i).getUserID()){
				found=true;
				index=i;
			}
		}
		if(found==true){
			users.remove(index);
			saveInfo();
		}else
			throw new UserNotFoundException("No user was found with id: "+userId);
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

	public void checkUser(UserDTO user) throws DALException {

		int tempID = user.getUserID();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserID() == tempID)
				throw new InvalidIDException("Wrong userID");
		}
		String tempName = user.getUserName();

		if (tempName.length() > 20 && tempName.length() < 2)
			throw new invalidUserNameException("Wrong name");

		String tempIni = user.getIni();

		for (int i = 0; i < users.size(); i++) {
			if (tempIni.equals(users.get(i).getIni()))
				throw new InvalidINIException("Wrong Initial");
		}
		String tempCPR = user.getCpr();

		if (checkCpr(tempCPR) == false) {
			throw new InvalidCPRException("Wrong CPR");
		}
		
		List<String> tempRoles = user.getRoles();
		
		if(tempRoles.size()==0)
			throw new noRoleException("Choose a role");
			

	}
}
