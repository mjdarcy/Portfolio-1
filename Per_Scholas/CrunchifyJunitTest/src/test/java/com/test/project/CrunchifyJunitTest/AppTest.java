package com.test.project.CrunchifyJunitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    //@Test
    //public void shouldAnswerWithTrue()
    //{
    //    assertTrue( true );
    //}
    
    @Test
	public void testStudent() {
		Student a = new Student();
		a.setId(1);
		a.setEmail("mgabriel@perscholas.org");
		a.setfName("Mike");
		a.setlName("Gabriel");
		
		//Student b = a;  why does this return true even without overloading?
		
		Student b = new Student();
		b.setId(1);
		b.setEmail("mgabriel@perscholas.org");
		b.setfName("Mike");
		b.setlName("Gabriel");		
		
		assertEquals(a, b);
		//assertSame(a, b);
	}


}
