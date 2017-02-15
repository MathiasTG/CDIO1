package dto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO;
import exceptions.DALException;

public class UserStore implements IUserDAO {
		
	private List<UserDTO> users;
	private String theString = "The value of that string";
    private int    someInteger = 0;
	
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
	
public static void main( String [] args ) throws IOException  { 

	String aString = "The value of that string";
    int    someInteger = 999;
   // SS instance = new SS();
    ObjectOutputStream oos = new ObjectOutputStream( 
                           new FileOutputStream(new File("UserInfo.ser")));

    // do the magic  
    oos.writeObject( aString + " " + someInteger );
    // close the writing.
    oos.close();
    ObjectInputStream ois = new ObjectInputStream(
			new FileInputStream("UserInfo.ser"));
    try {
		System.out.println(""+ ois.readObject());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    ois.close();
    	}
}
