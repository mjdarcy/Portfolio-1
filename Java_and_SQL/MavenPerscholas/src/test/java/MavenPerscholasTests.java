import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;

public class MavenPerscholasTests {

	@Test
	public void getNameAtIndex()
	{
		MavenPerscholas m = new MavenPerscholas();
		assertEquals(m.getNames().get(2), "Tony");
		assertEquals(m.getNames().get(0), "Mike");
	}
	
	@Test
	public void addName()
	{
		MavenPerscholas m = new MavenPerscholas();
		m.addName("Jared");
		ArrayList<String> arr = m.getNames();
		assertEquals("Jared", arr.get(arr.size() - 1));
	}
}

/*
Tests for getNameAtIndex()
Test 1 Data: 
Input = 2
Expected = “Tony”
Test 2 Data:
Input = 0
Expected = “Mike”
Test for addName()
Note: use getNames() to check results
Test Data:
Input = “Jared”
Expected = Last entry of getNames() arraylist should be “Jared"
*/