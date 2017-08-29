package scheduleMaker;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

public class ShiftHandler {
	
	public static Shift handleShift(Shift shift) {
		if (shift.getWorkersNum() == 0) {
			shift.markAsUnwanted();
			return shift;
		}
		
		Map<Worker, Preference> workers = shift.getWorkersMap();
		Set<Entry<Worker, Preference>> workerSet = workers.entrySet();
		
		PriorityQueue<Worker> preferringWorkers = new PriorityQueue<Worker>();
		PriorityQueue<Worker> availableWorkers = new PriorityQueue<Worker>();
		
		for (Entry<Worker, Preference> ent: workerSet) {
			if (ent.getValue() == Preference.P) {
				preferringWorkers.add(ent.getKey());
			}
			
			if (ent.getValue() == Preference.A) {
				availableWorkers.add(ent.getKey());
			}
		}
		
		if (preferringWorkers.isEmpty()) {
			Worker aWorker = availableWorkers.peek();
			aWorker.addAssignedShift(shift.getShiftId());
			shift.updateWorker(aWorker);
			shift.assignWorker(aWorker);
			return shift;
		}
		
		if (availableWorkers.isEmpty()) {
			Worker pWorker = preferringWorkers.peek();
			pWorker.addAssignedShift(shift.getShiftId());
			shift.updateWorker(pWorker);
			shift.assignWorker(pWorker);
			return shift;
		}
		
		
		Worker pWorker = preferringWorkers.peek();
		Worker aWorker = availableWorkers.peek();
		
		if (pWorker.compareTo(aWorker) <= 0) {
			pWorker.addAssignedShift(shift.getShiftId());
			shift.updateWorker(pWorker);
			shift.assignWorker(pWorker);
			return shift;
		}
		
		aWorker.addAssignedShift(shift.getShiftId());
		shift.updateWorker(aWorker);
		shift.assignWorker(aWorker);
		return shift;		
	}
}
