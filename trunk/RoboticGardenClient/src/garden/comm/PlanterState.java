package garden.comm;

import utils.Converter;

public class PlanterState {
	
	public static int MSG_LENGTH = 15;
	
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
		byte temp[] = new byte[4];
		
		// Convert the message.
		planterID = (int)(b[0] & 0xFF);
		
		System.arraycopy(b, 1, temp, 0, 4);
		temperature = Float.intBitsToFloat(Converter.byteArrayToInt(temp));
		
		System.arraycopy(b, 5, temp, 0, 4);
		moistureLevel = Float.intBitsToFloat(Converter.byteArrayToInt(temp));
		
		System.arraycopy(b, 9, temp, 0, 4);
		gdhProgress = Float.intBitsToFloat(Converter.byteArrayToInt(temp));
		
		lightState = Light.State.values()[(int)(b[13] & 0xFF)];
		
		plantType = Plant.Type.values()[(int)(b[14] & 0xFF)];
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
