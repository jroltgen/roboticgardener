package garden.comm;

import java.util.Vector;

public class GardenStateMessage {

	private int numPlanters;
	private Vector<PlanterState> planterStates;
	
	public GardenStateMessage(int numPlanters, Vector<PlanterState> ps) {
		this.numPlanters = numPlanters;
		planterStates = ps;
	}

	public int getNumPlanters() {
		return numPlanters;
	}

	public Vector<PlanterState> getPlanterStates() {
		return planterStates;
	}
}
