package test;

import dto.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserStoreTest {
	private List<UserDTO> users;
	String pathName = "testData.ser";
	private UserStore data = new UserStore(pathName);
	List<String> roles;
	UserDTO user1, user2, user3;
	boolean testStatus = false;

	@Before
	public void setup() {

		roles = new ArrayList<String>();
		roles.add("Admin");
		user1 = new UserDTO(11, "DetteErNumber1", "EN", "111111-1111", "dsafadddf", roles);
		user2 = new UserDTO(12, "DetteErNumber2", "EN", "221111-1111", "la,fsasds", roles);

		user3 = new UserDTO(13, "DetteErNumber3", "EN", "121111-1111", "asfdad", roles);
		
	}

	// @After
	// public void teardown() {
	//
	// }

	@Test
	public void testDatabase() {
		data.loadInfo();

		try {
			data.createUser(user1);

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("taber");

		}

		try {
			if (data.getUser(11).getCpr().equals("11")) {
				testStatus = true;
			}
		} catch (Exception e) {
			System.out.println("Mega taber");
		}
		Assert.assertTrue(testStatus);

	}

}
