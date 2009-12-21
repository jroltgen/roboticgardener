package garden.comm;

import utils.Converter;

public class PlanterState {
	private float temperature; // in degrees F
	private float moistureLevel; // Between 0 and 1
	private float gdhProgress; // Between 0 and 1
	private LightState lightState; // one of LightState.java
	private PlantType plantType; // one of PlantType.java

	public PlanterState(float temp, float moisture, float gdh, LightState ls,
			PlantType p) {
		temperature = temp;
		moistureLevel = moisture;
		gdhProgress = gdh;
		lightState = ls;
		plantType = p;
	}

	public PlanterState(byte[] b) {
		byte temp[] = new byte[4];
		
		// Convert the message.
		System.arraycopy(b, 0, temp, 0, 4);
		temperature = Float.intBitsToFloat(Converter.byteArrayToInt(temp));
		
		System.arraycopy(b, 4, temp, 0, 4);
		moistureLevel = Float.intBitsToFloat(Converter.byteArrayToInt(temp));
		
		System.arraycopy(b, 8, temp, 0, 4);
		gdhProgress = Float.intBitsToFloat(Converter.byteArrayToInt(temp));
		
		lightState = LightState.values()[(int)(b[12] & 0xFF)];
		
		plantType = PlantType.values()[(int)(b[13] & 0xFF)];
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

	public LightState getLightState() {
		return lightState;
	}

	public void setLightState(LightState lightState) {
		this.lightState = lightState;
	}

	public PlantType getPlantType() {
		return plantType;
	}

	public void setPlantType(PlantType plantType) {
		this.plantType = plantType;
	}

	public float getGdhProgress() {
		return gdhProgress;
	}

	public void setGdhProgress(float gdhProgress) {
		this.gdhProgress = gdhProgress;
	}
}
