package garden.comm;

import java.util.Vector;

public class GardenStateMessage {

	private int numPlanters;
	private float waterLevel;
	private Vector<PlanterState> planterStates;

	public GardenStateMessage(int numPlanters, float waterLevel,
			Vector<PlanterState> ps) {
		this.numPlanters = numPlanters;
		this.waterLevel = waterLevel;
		planterStates = ps;
	}

	public int getNumPlanters() {
		return numPlanters;
	}

	public Vector<PlanterState> getPlanterStates() {
		return planterStates;
	}
	
	public float getWaterLevel() {
		return waterLevel;
	}
}
