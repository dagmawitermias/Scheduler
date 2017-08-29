package scheduleMaker;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShiftHandlerTest {

	Shift myShift = new Shift("m4");
	Worker dagmawit = new Worker("dagmawit", 5);
	Worker julie = new Worker("julie", 5);
	
	@Test
	public void testHandleShift() {
		Shift handledShift = ShiftHandler.handleShift(myShift);
		assertTrue(handledShift.isUnwanted());
	}
	
	@Test
	public void testSinglePrefferingWorker() {
		myShift.addWorker(dagmawit, Preference.P);
		Shift handledShift = ShiftHandler.handleShift(myShift);
		assertEquals(handledShift.getAssignedWorker(), dagmawit);

	}
	
	@Test
	public void testSingleAvailableWorker() {
		myShift.addWorker(dagmawit, Preference.A);
		Shift handledShift = ShiftHandler.handleShift(myShift);
		assertEquals(handledShift.getAssignedWorker(), dagmawit);
	}
	
	@Test
	public void testPreferringWorkerWins() {
		myShift.addWorker(dagmawit, Preference.A);
		myShift.addWorker(julie, Preference.P);
		Shift handledShift = ShiftHandler.handleShift(myShift);
		assertEquals(handledShift.getAssignedWorker(), julie);
	}
	
	@Test
	public void testAvailableWorkerWins() {
		julie.addAssignedShift("m3");
		myShift.addWorker(dagmawit, Preference.A);
		myShift.addWorker(julie, Preference.P);
		Shift handledShift = ShiftHandler.handleShift(myShift);
		assertEquals(handledShift.getAssignedWorker(), dagmawit);
	}
	
	@Test
	public void testTwoPreferringWorkers() {
		julie.addAssignedShift("m3");
		myShift.addWorker(dagmawit, Preference.P);
		myShift.addWorker(julie, Preference.P);
		Shift handledShift = ShiftHandler.handleShift(myShift);
		assertEquals(handledShift.getAssignedWorker(), dagmawit);
	}
	
	@Test
	public void testTwoAvailableWorkers() {
		julie.addAssignedShift("m3");
		myShift.addWorker(dagmawit, Preference.A);
		myShift.addWorker(julie, Preference.A);
		Shift handledShift = ShiftHandler.handleShift(myShift);
		assertEquals(handledShift.getAssignedWorker(), dagmawit);
	}
	

}
