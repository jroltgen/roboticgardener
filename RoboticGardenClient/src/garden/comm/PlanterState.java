package garden.comm;

public class PlanterState {
	
	public static int MSG_LENGTH = 6;
	
	private float temperature; // in degrees F
	private float moistureLevel; // Between 0 and 1
	private float gdhProgress; // Between 0 and 1
	private Light.State lightState; // one of LightState.java
	private Plant.Type plantType; // one of PlantType.java
	private int planterID; // a byte on the input stream.

	public PlanterState(float temp, float moisture, float gdh, Light.State ls,
			Plant.Type p, int id) {
		planterID = id;
		temperature = temp;
		moistureLevel = moisture;
		gdhProgress = gdh;
		lightState = ls;
		plantType = p;
	}

	public PlanterState(byte[] b) {
		// Convert the message.
		planterID = (int)(b[0] & 0xFF);
		temperature = (int)(b[1] & 0xFF) - 80;
		moistureLevel = ((int)(b[2] & 0xFF) == 0) ? 0 : 1;
		gdhProgress = (int)(b[3] & 0xFF) / 255.0f;
		lightState = Light.State.values()[(int)(b[4] & 0xFF)];
		plantType = Plant.Type.values()[(int)(b[5] & 0xFF)];
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getMoistureLevel() {
		return moistureLevel;
	}

	public void setMoistureLevel(float moistureLevel) {
		this.moistureLevel = moistureLevel;
	}

	public Light.State getLightState() {
		return lightState;
	}

	public void setLightState(Light.State lightState) {
		this.lightState = lightState;
	}

	public Plant.Type getPlantType() {
		return plantType;
	}

	public void setPlantType(Plant.Type plantType) {
		this.plantType = plantType;
	}

	public float getGdhProgress() {
		return gdhProgress;
	}

	public void setGdhProgress(float gdhProgress) {
		this.gdhProgress = gdhProgress;
	}
	
	public int getID() {
		return planterID;
	}
	
	public void setID(int id) {
		this.planterID = id;
	}
}
