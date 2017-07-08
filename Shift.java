package scheduleMaker;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Shift implements Comparable<Shift> {
	
	private final String shiftId;
	private Worker assignedWorker;
	private int numOfAvailable;
	private int numOfPreferred;
	private int numOfWorkers;
	private Map<Worker, Preference> workers;
	private boolean isAssigned;
	private boolean isUnwanted;
	
	
	public Shift(String shiftId) {
		this.shiftId = shiftId;
		assignedWorker = new Worker("Dagmawit", 0);
		numOfAvailable = 0;
		numOfPreferred = 0;
		numOfWorkers = 0;
		isAssigned = false;
		isUnwanted = false; 
		workers = new HashMap<Worker, Preference>();
	}
	
	public void assignWorker(Worker worker) {
		assignedWorker = worker;
		isAssigned = true;
	}
	
	public Worker getAssignedWorker() {
		return assignedWorker;
	}
	
	public String getShiftId() {
		return shiftId;
	}
	
	public boolean isAssigned() {
		return isAssigned;
	}
	
	public void markAsAssigned() {
		isAssigned = true;
		isUnwanted = false;
	}
	
	public boolean isUnwanted() {
		return isUnwanted;
	}
	
	public void markAsUnwanted() {
		isAssigned = false;
		isUnwanted = true;
	}

	public int getAvailableNum() {
		return numOfAvailable;
	}
	
	public int getPreferredNum() {
		return numOfPreferred;
	}
	
	public int getWorkersNum() {
		return numOfWorkers;
	}	
	
	public void addWorker(Worker worker, Preference pref) {
		boolean exists = false;
		if (workers.containsKey(worker)) {
			exists = true; 
			if (workers.get(worker) == pref) {
				return;
			}
		}
		
		workers.put(worker, pref);		
		
		//if only the preference of an existing worker was changed 
		if (exists) {
			if (pref == Preference.P){
				numOfPreferred++;
				numOfAvailable--;
				return;
			}
			if (pref == Preference.A) {
				numOfAvailable++;
				numOfPreferred--;
				return;
			}
		}
		
		//if a new worker was added
		if (pref == Preference.P) {
			numOfPreferred++;
		}
		
		if (pref == Preference.A) {
			numOfAvailable++;
		}
		
		numOfWorkers++;		
	}
	
	public boolean isWorkerAvailable(Worker worker) {
		if (workers.containsKey(worker)) {
			if (workers.get(worker) == Preference.A) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isWorkersPreferred(Worker worker) {
		if (workers.containsKey(worker)) {
			if (workers.get(worker) == Preference.P) {
				return true;
			}
		}
		
		return false;
	}	
	
	public Set<Worker> getWorkers() {
		Set<Worker> workerSet = workers.keySet();
		return workerSet;
	}
	
	public Map<Worker, Preference> getWorkersMap() {
		return workers;
	}

	//Note: this class has a natural ordering that is inconsistent with equals
	@Override
	public int compareTo(Shift o) {
		if (this.numOfWorkers < o.getWorkersNum()) {
			return -1;
		}
		
		if (this.numOfWorkers > o.getWorkersNum()) {
			return 1; 
		}
		
		return 0;
	}
	
}