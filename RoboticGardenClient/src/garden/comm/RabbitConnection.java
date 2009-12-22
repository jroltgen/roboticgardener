package garden.comm;

import garden.GardenClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 * Connects to the rabbit board. Note: many calls are made to the stream's
 * writeByte() method, passing in an integer. In java, bytes are signed, so we
 * will want to be careful how these are interpreted in the case that the value
 * is between 128 and 255.
 * 
 * @author Jay Roltgen (jroltgen@gmail.com)
 * 
 */
public class RabbitConnection {

	private Socket _socket;
	private DataInputStream _input;
	private DataOutputStream _output;

	public RabbitConnection() {

	}

	public boolean connect(String hostName) {
		try {
			_socket = new Socket(InetAddress.getByName(hostName),
					GardenClient.PORT);
			_input = new DataInputStream(_socket.getInputStream());
			_output = new DataOutputStream(_socket.getOutputStream());
			// Begin listening.
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public GardenStateMessage getGardenState() throws IOException {
		_output.writeByte(MessageTypes.Outgoing.GET_STATE.ordinal());

		// Get the message ID.
		byte b = _input.readByte();
		MessageTypes.Incoming type = MessageTypes.Incoming
				.values()[(int) (b & 0xFF)];
		if (type != MessageTypes.Incoming.GARDEN_STATE) {
			System.err.println("Incorrect message received: type : " + type);
			System.exit(0);
		}

		// Get the number of planters
		int numPlanters = (int) (_input.readByte() & 0xFF);

		Vector<PlanterState> states = new Vector<PlanterState>();
		byte[] buf = new byte[14];

		for (int i = 0; i < numPlanters; i++) {
			_input.read(buf, 0, PlanterState.MSG_LENGTH);
			states.add(new PlanterState(buf));
		}

		return new GardenStateMessage(numPlanters, states);
	}

	public void water(int planterID) throws IOException {
		_output.writeByte(MessageTypes.Outgoing.WATER.ordinal());
		_output.writeByte(planterID);
	}
	
	public void replant(int planterID) throws IOException {
		_output.writeByte(MessageTypes.Outgoing.REPLANT.ordinal());
		_output.writeByte(planterID);
	}
}
