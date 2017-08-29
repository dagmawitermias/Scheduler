package scheduleMaker;

import java.util.PriorityQueue;
import java.util.Set;

public class Scheduler {
	
	private PriorityQueue<Shift> shifts;
	private Set<Shift> unWantedShifts;
	private Set<Shift> assignedShifts;
	
	public Scheduler (PriorityQueue<Shift> shifts) {
		this.shifts = shifts;
	}
	
	public void scheduleShifts() {
		while(!shifts.isEmpty()) {
			Shift curr = shifts.poll();
			Shift handledShift = ShiftHandler.handleShift(curr);
			if (handledShift.isUnwanted()) {
				unWantedShifts.add(handledShift);
			}
			
			if (handledShift.isAssigned()) {
				Worker assignedWorker = handledShift.getAssignedWorker();
				
				for (Shift shift: shifts) {
					shift.updateWorker(assignedWorker);
				}
				
				assignedShifts.add(handledShift);
			}
		}
	}
	
	public Set<Shift> getUnwantedShifts() {
		return unWantedShifts;
	}
	
	public Set<Shift> getAssignedShifts() {
		return assignedShifts;
	}
	
}
