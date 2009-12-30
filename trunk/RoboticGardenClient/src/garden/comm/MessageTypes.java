package garden.comm;

public class MessageTypes {
	
	public static enum Incoming {
		GARDEN_STATE, ERROR
	}
	
	public static enum Outgoing {
		GET_STATE, WATER, REPLANT
	}
}
