package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
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
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dto.UserDTO;
import dto.UserStore;

public class UserStoreTest {
	private List<UserDTO> users;
	String pathName = "testData.ser";

	@Before
	public void setup() {
		users = null;
		saveInfo();
		UserStore data = new UserStore(pathName);
		loadInfo();

	}

	@After
	public void teardown() {
		users = null;
		saveInfo();
	}

	@Test
	public void testDatabase() {
		list<String> roles = new ArrayList<String>();
		
		UserDTO user1  = new UserDTO(11, "Jonas Larsen" "JL", "111111-1111", "1234556Jj!", roles)
		users.add()

		
		
		
		Assert.assertNotNull(this.aw4);

		// Tests to see if this is a valid instance of Labor Camp.
		Assert.assertTrue(this.aw1 instanceof LaborCamp);
		Assert.assertTrue(this.aw2 instanceof LaborCamp);
		Assert.assertTrue(this.aw3 instanceof LaborCamp);
		Assert.assertTrue(this.aw4 instanceof LaborCamp);
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

	@SuppressWarnings("unchecked")
	public void loadInfo() {

		try {
			InputStream file = new FileInputStream(pathName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			// ois = new ObjectInputStream(new FileInputStream("UserInfo.ser"));
			users = (ArrayList<UserDTO>) input.readObject();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			users = new ArrayList();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
