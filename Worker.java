package scheduleMaker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Worker implements Comparable<Worker> {
	
	private final String name;
	private Map<Shift, Preference> wantedShifts;
	private Set<Shift> assignedShifts;
	private final int prefNumOfShifts;
	private int wantedShiftsNum;
	private int assignedShiftsNum;
	
	
	public Worker(String name, int prefNumOfShifts) {
		this.name = name;
		wantedShifts = new HashMap<Shift, Preference>();
		assignedShifts = new HashSet<Shift>();
		this.prefNumOfShifts = prefNumOfShifts; 
		wantedShiftsNum = 0;
		assignedShiftsNum = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrefNumOfShifts() {
		return prefNumOfShifts;
	}
	
	public void addWantedShift(Shift shift, Preference pref) {
		wantedShifts.put(shift, pref);
		wantedShiftsNum++;
	}
	
	public void addAssignedShift(Shift shift) {
		assignedShifts.add(shift);
		assignedShiftsNum++;
	}
	
	public boolean hasPrefNumOfShifts() {
		if (assignedShiftsNum == prefNumOfShifts) {
			return true; 
		}
		return false;
	}
	
	public boolean hasShiftAssigned(Shift shift) {
		return assignedShifts.contains(shift);
	}
	
	public boolean wantsShift(Shift shift) {
		return wantedShifts.containsKey(shift);
	}
	
	public boolean prefersShift(Shift shift) {
		if (wantedShifts.containsKey(shift)) {
			if (wantedShifts.get(shift) == Preference.P) {
				return true;
			}
		}
		return false;
	}
	
	public int getAssignedShiftsNum() {
		return assignedShiftsNum;
	}
	
	public int getWantedShiftsNum() {
		return wantedShiftsNum;
	}
	
	//Note: this class has a natural ordering that is inconsistent with equals
	@Override
	public int compareTo(Worker o) {
		int w1 = prefNumOfShifts - assignedShiftsNum;
		int w2 = o.getPrefNumOfShifts() - o.getAssignedShiftsNum(); 
		
		if (w1 > w2) {
			return -1;
		}
		
		if (w1 < w2) {
			return 1;
		}	
		
		return 0;
	}
}