package test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dto.UserStore;

public class UserStoreTest {

	@Before
	public void setup() {

		UserStore d = new UserStore();
		d.loadInfo();
		
	}

	@After
	public void teardown() {

	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.p1);
		Assert.assertNotNull(this.p2);

		Assert.assertNotNull(this.aw1);
		Assert.assertNotNull(this.aw2);
		Assert.assertNotNull(this.aw3);
		Assert.assertNotNull(this.aw4);

		// Tests to see if this is a valid instance of Labor Camp.
		Assert.assertTrue(this.aw1 instanceof LaborCamp);
		Assert.assertTrue(this.aw2 instanceof LaborCamp);
		Assert.assertTrue(this.aw3 instanceof LaborCamp);
		Assert.assertTrue(this.aw4 instanceof LaborCamp);
	}

}
