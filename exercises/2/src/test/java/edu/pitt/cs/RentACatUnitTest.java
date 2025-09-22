package edu.pitt.cs;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatUnitTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	ByteArrayOutputStream out; // Output stream for testing system output
	PrintStream stdout; // Print stream to hold the original stdout stream
	String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n") for use in assertEquals

	@Before
	public void setUp() throws Exception {
		// INITIALIZE THE TEST FIXTURE
		
		// 1. Create a new RentACat object and assign to r using a call to RentACat.createInstance(InstanceType).
		// Passing InstanceType.IMPL as the first parameter will create a real RentACat object using your RentACatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock RentACat object using Mockito.
		// Which type is the correct choice for this unit test?  I'll leave it up to you.  The answer is in the Unit Testing Part 2 lecture. :)
		// TODO: Fill in
		r = RentACat.createInstance(InstanceType.IMPL);

		// 2. Create a Cat with ID 1 and name "Jennyanydots", assign to c1 using a call to Cat.createInstance(InstanceType, int, String).
		// Passing InstanceType.IMPL as the first parameter will create a real cat using your CatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock cat using Mockito.
		// Which type is the correct choice for this unit test?  Again, I'll leave it up to you.
		// TODO: Fill in
		c1 = Cat.createInstance(InstanceType.IMPL, 1, "Jennyanydots");
		// 3. Create a Cat with ID 2 and name "Old Deuteronomy", assign to c2 using a call to Cat.createInstance(InstanceType, int, String).
		// TODO: Fill in
		c2 = Cat.createInstance(InstanceType.IMPL, 2, "Old Deuteronomy");
		// 4. Create a Cat with ID 3 and name "Mistoffelees", assign to c3 using a call to Cat.createInstance(InstanceType, int, String).
		// TODO: Fill in
		c3 = Cat.createInstance(InstanceType.IMPL, 3, "Mistoffelees");
		
		// 5. Redirect system output from stdout to the "out" stream
		// First, make a back up of System.out (which is the stdout to the console)
		stdout = System.out;
		// Second, update System.out to the PrintStream created from "out"
		// TODO: Fill in.  Refer to the textbook chapter 14.6 on Testing System Output.
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@After
	public void tearDown() throws Exception {
		// Restore System.out to the original stdout
		System.setOut(stdout);

		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix 
	 * hapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNullNumCats0() {
		// TODO: Fill in
		try{
		Method method = r.getClass().getDeclaredMethod("getCat", int.class);
		method.setAccessible(true);
		Object returnValue = method.invoke(r, 2);
		assertNull(returnValue);
		String output = out.toString();
        assertEquals("Invalid cat ID." + System.lineSeparator(), output);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        fail("Reflection test failed: " + e.getMessage());
    }
}



	

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix 
	 * hapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNumCats3() {
		// TODO: Fill in
			r.addCat(c1);
			r.addCat(c2);
			r.addCat(c3);
			
			try {
				Method method = RentACatImpl.class.getDeclaredMethod("getCat", int.class);
				method.setAccessible(true);
				Object returnValue = method.invoke(r, 2);
				assertNotNull("getCat returned null for ID 2", returnValue);
				assertEquals(2, ((Cat) returnValue).getId());
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
				// Print the actual cause of the reflection failure
				fail("Reflection test failed: " + e.getCause());
			}
}


	

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats0() {
		// TODO: Fill in
		String res = r.listCats();
		assertEquals("",res);
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats3() {
		// TODO: Fill in
		r = new RentACatImpl(); // make sure it's the concrete class
    r.addCat(c1);
    r.addCat(c2);
    r.addCat(c3);

    try {
        Method method = RentACatImpl.class.getDeclaredMethod("getCat", int.class);
        method.setAccessible(true);
        Object returnValue = method.invoke(r, 2);
        assertNotNull("getCat returned null for ID 2", returnValue);
        assertEquals(2, ((Cat) returnValue).getId());
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        fail("Reflection test failed: " + e);
    }
		
}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is false.
	 *                 c2 is not renamed to "Garfield".
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRenameFailureNumCats0() {
		// TODO: Fill in
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		boolean res = r.renameCat(2, "Oreo");
		assertFalse(res); // Should return false
    	assertEquals("Invalid cat ID." + System.lineSeparator(),
                 outContent.toString());



		}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is true.
	 *                 c2 is renamed to "Garfield".
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRenameNumCat3() {
		// TODO: Fill in
		c1 = Mockito.mock(Cat.class);
		c2 = Mockito.mock(Cat.class);
		c3 = Mockito.mock(Cat.class);

		Mockito.when(c1.getId()).thenReturn(1);
		Mockito.when(c2.getId()).thenReturn(2);
		Mockito.when(c3.getId()).thenReturn(3);

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		boolean res = r.renameCat(2, "Garfield");
		assertTrue(res);

		Mockito.verify(c2).renameCat("Garfield");
		Mockito.verify(c1, Mockito.never()).renameCat(Mockito.anyString());
		Mockito.verify(c3, Mockito.never()).renameCat(Mockito.anyString());
		}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is rented as a result of the execution steps.
	 *                 System output is "Old Deuteronomy has been rented." + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRentCatNumCats3() {
		// TODO: Fill in
		c1 = Mockito.mock(Cat.class);
		c2 = Mockito.mock(Cat.class);
		c3 = Mockito.mock(Cat.class);

		Mockito.when(c1.getId()).thenReturn(1);
		Mockito.when(c2.getId()).thenReturn(2);
		Mockito.when(c3.getId()).thenReturn(3);
		Mockito.when(c1.getRented()).thenReturn(false);
		Mockito.when(c2.getRented()).thenReturn(false);
		Mockito.when(c3.getRented()).thenReturn(false);
		Mockito.when(c1.getName()).thenReturn("Jennyanydots");
		Mockito.when(c2.getName()).thenReturn("Old Deuteronomy");
		Mockito.when(c3.getName()).thenReturn("Mistoffelees");

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		boolean res = r.rentCat(2);
		assertTrue(res);

		Mockito.verify(c2).rentCat();
		Mockito.verify(c1, Mockito.never()).rentCat();
		Mockito.verify(c3, Mockito.never()).rentCat();

		assertEquals("Old Deuteronomy has been rented." + System.lineSeparator(), outContent.toString());


	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 is not rented as a result of the execution steps.
	 *                 System output is "Sorry, Old Deuteronomy is not here!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRentCatFailureNumCats3() {
		// TODO: Fill in
		c1 = Mockito.mock(Cat.class);
		c2 = Mockito.mock(Cat.class);
		c3 = Mockito.mock(Cat.class);

		Mockito.when(c1.getId()).thenReturn(1);
		Mockito.when(c2.getId()).thenReturn(2);
		Mockito.when(c3.getId()).thenReturn(3);
		Mockito.when(c1.getRented()).thenReturn(false);
		Mockito.when(c2.getRented()).thenReturn(true); 
		Mockito.when(c3.getRented()).thenReturn(false);
		Mockito.when(c1.getName()).thenReturn("Jennyanydots");
		Mockito.when(c2.getName()).thenReturn("Old Deuteronomy");
		Mockito.when(c3.getName()).thenReturn("Mistoffelees");

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		boolean res = r.rentCat(2);
		assertFalse(res);

		Mockito.verify(c2, Mockito.never()).rentCat(); 
		Mockito.verify(c1, Mockito.never()).rentCat();
		Mockito.verify(c3, Mockito.never()).rentCat();

		assertEquals("Sorry, Old Deuteronomy is not here!" + System.lineSeparator(), outContent.toString());

	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is returned as a result of the execution steps.
	 *                 System output is "Welcome back, Old Deuteronomy!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testReturnCatNumCats3() {
		// TODO: Fill in
		c1 = Mockito.mock(Cat.class);
		c2 = Mockito.mock(Cat.class);
		c3 = Mockito.mock(Cat.class);

		Mockito.when(c1.getId()).thenReturn(1);
		Mockito.when(c2.getId()).thenReturn(2);
		Mockito.when(c3.getId()).thenReturn(3);

		Mockito.when(c1.getRented()).thenReturn(false);
		Mockito.when(c2.getRented()).thenReturn(true); 
		Mockito.when(c3.getRented()).thenReturn(false);

		Mockito.when(c1.getName()).thenReturn("Jennyanydots");
		Mockito.when(c2.getName()).thenReturn("Old Deuteronomy");
		Mockito.when(c3.getName()).thenReturn("Mistoffelees");

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		boolean res = r.returnCat(2);
		assertTrue(res);

		Mockito.verify(c2).returnCat();
		Mockito.verify(c1, Mockito.never()).returnCat();
		Mockito.verify(c3, Mockito.never()).returnCat();

		assertEquals("Welcome back, Old Deuteronomy!" + System.lineSeparator(), outContent.toString());

	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 is not returned as a result of the execution steps.
	 *                 System output is "Old Deuteronomy is already here!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testReturnFailureCatNumCats3() {
		// TODO: Fill in
		c1 = Mockito.mock(Cat.class);
		c2 = Mockito.mock(Cat.class);
		c3 = Mockito.mock(Cat.class);

		Mockito.when(c1.getId()).thenReturn(1);
		Mockito.when(c2.getId()).thenReturn(2);
		Mockito.when(c3.getId()).thenReturn(3);

		Mockito.when(c1.getRented()).thenReturn(false);
		Mockito.when(c2.getRented()).thenReturn(false); 
		Mockito.when(c3.getRented()).thenReturn(false);

		Mockito.when(c1.getName()).thenReturn("Jennyanydots");
		Mockito.when(c2.getName()).thenReturn("Old Deuteronomy");
		Mockito.when(c3.getName()).thenReturn("Mistoffelees");

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		boolean result = r.returnCat(2);
		assertFalse(result);

		Mockito.verify(c1, Mockito.never()).returnCat();
		Mockito.verify(c2, Mockito.never()).returnCat();
		Mockito.verify(c3, Mockito.never()).returnCat();

		assertEquals("Old Deuteronomy is already here!" + System.lineSeparator(), outContent.toString());
			}

	}